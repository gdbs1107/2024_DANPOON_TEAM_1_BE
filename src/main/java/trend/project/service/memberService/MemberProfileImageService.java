package trend.project.service.memberService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import trend.project.domain.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public interface MemberProfileImageService {


    String upload(MultipartFile multipartFile, String dirName, String username) throws IOException;

    File convert(MultipartFile file) throws IOException;

    String putS3(File uploadFile, String fileName);

    void removeNewFile(File targetFile);

    void saveFileMetadata(String originalFileName, String uniqueFileName, long fileSize, String contentType,
                          Member findMember, String uploadImageUrl);

    byte[] download(String username) throws IOException;

    // memberId로 이미지 조회
    byte[] downloadImage(Long memberId) throws IOException;

    @Transactional
    void deleteFile(String username) throws FileNotFoundException;

    String updateProfileImage(MultipartFile newImageFile, String dirName, String username) throws IOException;
}
