package trend.project.service.planService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import trend.project.domain.Plan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public interface PlanBannerService {
    
    String upload(MultipartFile multipartFile, String dirName, Long planId, String username) throws IOException;
    
    File convert(MultipartFile file) throws IOException;
    
    String putS3(File uploadFile, String fileName);
    
    void removeNewFile(File targetFile);
    
    void saveFileMetadata(String originalFileName, String uniqueFileName, long fileSize, String contentType,
                          Plan findplan);
    
    byte[] download(Long planId) throws IOException;
    
    @Transactional
    void deleteFile(Long planId, String username) throws FileNotFoundException;
    
    String updateBannerImage(MultipartFile newImageFile, String dirName, Long planId, String username) throws IOException;
}
