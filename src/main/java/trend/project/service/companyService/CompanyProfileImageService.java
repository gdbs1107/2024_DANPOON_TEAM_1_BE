package trend.project.service.companyService;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import trend.project.domain.Company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CompanyProfileImageService {
    String upload(MultipartFile multipartFile, String dirName, String username) throws IOException;

    File convert(MultipartFile file) throws IOException;

    String putS3(File uploadFile, String fileName);

    void removeNewFile(File targetFile);

    void saveFileMetadata(String originalFileName, String uniqueFileName, long fileSize, String contentType,
                          Company findCompany);

    byte[] download(String username) throws IOException;

    //companyId로 조회
    byte[] downloadImage(Long companyId) throws IOException;

    @Transactional
    void deleteFile(String username) throws FileNotFoundException;

    String updateProfileImage(MultipartFile newImageFile, String dirName, String username) throws IOException;
}
