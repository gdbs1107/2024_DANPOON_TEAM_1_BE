package trend.project.web.controller.companyController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import trend.project.api.ApiResponse;
import trend.project.service.companyService.CompanyProfileImageService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies/images")
@Slf4j
@Tag(name = "기업 프로필 이미지 관리 API")
public class CompanyImageController {

    private final CompanyProfileImageService companyProfileImageService;





    @Operation(summary = "프로필 사진 등록 API")
    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> uploadCompanyProfileImage(
            @RequestPart(value = "file") MultipartFile multipartFile,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {

        String username = userDetails.getUsername();

        String dirName="기업 프로필 이미지";
        String url = companyProfileImageService.upload(multipartFile, dirName, username);
        log.info("파일 업로드 완료: {}", url);

        return ApiResponse.onSuccess(url);
    }






    @Operation(summary = "본인 프로필 사진 조회 API")
    @GetMapping(path = "")
    public ResponseEntity<byte[]> getCompanyProfileImage(
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();

        try {
            byte[] fileData = companyProfileImageService.download(username);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "filename");
            log.info("파일 다운로드 완료: ID {}", username);

            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        } catch (Exception e) {

            log.error("파일 다운로드 오류: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @Operation(summary = "프로필 사진 조회 API")
    @GetMapping(path = "/{companyId}")
    public ResponseEntity<byte[]> getProfileImage(
            @PathVariable Long companyId) {


        try {
            byte[] fileData = companyProfileImageService.downloadImage(companyId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "filename");
            log.info("파일 다운로드 완료: ID {}", companyId);

            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        } catch (Exception e) {

            log.error("파일 다운로드 오류: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }







    @Operation(summary = "프로필 사진 삭제 API")
    @DeleteMapping(path = "")
    public ApiResponse<String> deleteCompanyProfileImage(
            @AuthenticationPrincipal UserDetails userDetails) {


        String username = userDetails.getUsername();


        try {

            companyProfileImageService.deleteFile(username);

            return ApiResponse.onSuccess("삭제에 성공하였습니다");

        } catch (Exception e) {
            log.error("파일 삭제 오류: {}", e.getMessage());
            return ApiResponse.onFailure("404","이미지를 찾을 수 없습니다",null);
        }
    }






    @Operation(summary = "프로필 사진 업데이트 API")
    @PutMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> updateCompanyProfileImage(
            @RequestPart(value = "file") MultipartFile multipartFile,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {


        String username = userDetails.getUsername();
        String dirName = "기업 프로필 이미지";


        try {

            String url = companyProfileImageService.updateProfileImage(multipartFile, dirName, username);
            log.info("프로필 이미지 업데이트 완료: {}", url);

            return ApiResponse.onSuccess(url);

        } catch (Exception e) {

            log.error("프로필 이미지 업데이트 오류: {}", e.getMessage());

            return ApiResponse.onFailure("404","이미지를 찾을 수 없습니다",null);
        }
    }



}
