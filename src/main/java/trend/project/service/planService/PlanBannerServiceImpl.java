package trend.project.service.planService;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.ImageCategoryHandler;
import trend.project.api.exception.handler.PlanCategoryHandler;
import trend.project.domain.Plan;
import trend.project.domain.PlanBannerImage;
import trend.project.repository.planRepository.BannerImageRepository;
import trend.project.repository.planRepository.PlanRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PlanBannerServiceImpl implements PlanBannerService {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final AmazonS3 amazonS3;
    
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final PlanRepository planRepository;
    private final BannerImageRepository bannerImageRepository;
    
    
    
    @Override
    public String upload(MultipartFile multipartFile, String dirName, Long planId, String username) throws IOException {
        
        Plan plan = getPlanById(planId);
        
        if (!plan.getMember().getUsername().equals(username)) {
            throw new PlanCategoryHandler(ErrorStatus.UNAUTHORIZED_ACTION);
        }
        
        String originalFileName = multipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String uniqueFileName = uuid +"_" + originalFileName.replaceAll("\\s", "_");
        String fileName = dirName + "/" + uniqueFileName;
        File uploadFile = convert(multipartFile);
        
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        
        // Save metadata to the database
        saveFileMetadata(originalFileName, uploadImageUrl, multipartFile.getSize(), multipartFile.getContentType(), plan);
        
        return uploadImageUrl;
    }
    
    @Override
    public File convert(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String uniqueFileName = uuid + "_" + originalFileName.replaceAll("\\s", "_");
        
        File convertFile = new File(uniqueFileName);
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            } catch (IOException e) {
                log.error("파일 변환 중 오류 발생: {}", e.getMessage());
                throw new ImageCategoryHandler(ErrorStatus.IMAGE_CONVERT_FAIL);
            }
            return convertFile;
        }
        throw new ImageCategoryHandler(ErrorStatus.IMAGE_CONVERT_FAIL);
    }
    
    @Override
    public String putS3(File uploadFile, String fileName) {
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucket, fileName).toString();
    }
    
    @Override
    public void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
            throw new ImageCategoryHandler(ErrorStatus.IMAGE_NOT_DELETED);
        }
    }
    
    @Override
    public void saveFileMetadata(String originalFileName, String uniqueFileName, long fileSize, String contentType, Plan findplan) {
        
        PlanBannerImage image = PlanBannerImage.builder()
                .imageLink(uniqueFileName)
                .imageName(originalFileName)
                .plan(findplan)
                .build();
        
        bannerImageRepository.save(image);
    }
    
    @Override
    public byte[] download(Long planId) throws IOException {
        
        PlanBannerImage planBannerImage = bannerImageRepository.findImagesByPlanId(planId)
                .orElseThrow(()->new ImageCategoryHandler(ErrorStatus.IMAGE_NOT_FOUND));
        
        String uniqueFileName = planBannerImage.getImageLink();  // Use full path as S3 key
        log.info("Downloading file from S3 with key: {}", uniqueFileName);
        
        try {
            S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket, uniqueFileName));
            try (S3ObjectInputStream inputStream = s3Object.getObjectContent()) {
                return inputStream.readAllBytes();
            }
        } catch (AmazonS3Exception e) {
            log.error("S3에서 파일 다운로드 오류: {}", e.getMessage());
            throw new ImageCategoryHandler(ErrorStatus.IMAGE_NOT_ALLOWED);
        }
    }
    
    @Override
    public void deleteFile(Long planId, String username) throws FileNotFoundException {
        try {
            Plan plan = getPlanById(planId);
            
            if (!plan.getMember().getUsername().equals(username)) {
                throw new PlanCategoryHandler(ErrorStatus.UNAUTHORIZED_ACTION);
            }
            
            // 배너 이미지 찾기
            PlanBannerImage planBannerImage = bannerImageRepository.findImagesByPlanId(planId)
                    .orElseThrow(()->new ImageCategoryHandler(ErrorStatus.IMAGE_NOT_FOUND));
            
            Long planBannerImageId = planBannerImage.getId();
            
            // 파일 경로(S3 키)
            String uniqueFileName = planBannerImage.getImageLink();
            if (uniqueFileName != null) {
                // AWS S3에서 파일 삭제
                amazonS3.deleteObject(bucket, uniqueFileName);
                
                planBannerImage.setPlan(null);
                
                log.info("Attempting to delete planBannerImage with ID: {}", planBannerImageId);
                
                // 로컬 DB에서 엔티티 삭제
                bannerImageRepository.deleteById(planBannerImageId);
                
                // 명시적으로 flush 호출
                entityManager.flush();
                // 1차 캐시 클리어
                entityManager.clear();
                log.info("Successfully deleted planBannerImage with ID: {}", planBannerImageId);
            } else {
                log.error("파일을 찾을 수 없습니다: ID {}", planBannerImageId);
            }
            
        } catch (Exception e) {
            log.error("파일 삭제 중 오류가 발생했습니다: ", e);
            throw new ImageCategoryHandler(ErrorStatus.IMAGE_NOT_DELETED);
        }
    }
    
    @Override
    public String updateBannerImage(MultipartFile newImageFile, String dirName, Long planId, String username) throws IOException {
        
        Plan findPlan = getPlanById(planId);
        
        if (!findPlan.getMember().getUsername().equals(username)) {
            throw new PlanCategoryHandler(ErrorStatus.UNAUTHORIZED_ACTION);
        }
        
        // 배너 이미지 삭제
        deleteFile(planId, username);
        
        // 새로운 이미지 업로드
        String originalFileName = newImageFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String uniqueFileName = uuid + "_" + originalFileName.replaceAll("\\s", "_");
        String fileName = dirName + "/" + uniqueFileName;
        File uploadFile = convert(newImageFile);
        
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        
        // 새로운 메타데이터 저장
        saveFileMetadata(originalFileName, uploadImageUrl, newImageFile.getSize(), newImageFile.getContentType(), findPlan);
        
        return uploadImageUrl;
    }
    
    
    public Plan getPlanById(Long planId){
        return planRepository.findById(planId)
                .orElseThrow(()-> new PlanCategoryHandler(ErrorStatus.PLAN_NOT_FOUND));
    }
}
