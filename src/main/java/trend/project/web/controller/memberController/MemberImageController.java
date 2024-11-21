package trend.project.web.controller.memberController;


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
import trend.project.service.memberService.MemberProfileImageService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/images")
@Slf4j
@Tag(name = "개인 프로필 이미지 관리 API")
public class MemberImageController {


    private final MemberProfileImageService memberProfileImageService;




    @Operation(summary = "프로필 사진 등록 API" , description = "Authorization 헤더에 토큰을 넣어주세요")
    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> uploadMemberProfileImage(
            @RequestPart(value = "file") MultipartFile multipartFile,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {

        String username = userDetails.getUsername();

        String dirName="회원 프로필 이미지";
        String url = memberProfileImageService.upload(multipartFile, dirName, username);
        log.info("파일 업로드 완료: {}", url);

        return ApiResponse.onSuccess(url);
    }





    @Operation(summary = "본인 프로필 사진 조회 API" , description = "Authorization 헤더에 토큰을 넣어주세요")
    @GetMapping(path = "")
    public ResponseEntity<byte[]> getMemberProfileImage(
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();

        try {
            byte[] fileData = memberProfileImageService.download(username);

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




    @Operation(summary = "프로필 사진 조회 API" , description = "Authorization 헤더에 토큰을 넣어주세요")
    @GetMapping(path = "/{memberId}")
    public ResponseEntity<byte[]> getProfileImage(
            @PathVariable Long memberId) {


        try {
            byte[] fileData = memberProfileImageService.downloadImage(memberId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "filename");
            log.info("파일 다운로드 완료: ID {}", memberId);

            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        } catch (Exception e) {

            log.error("파일 다운로드 오류: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






    @Operation(summary = "프로필 사진 삭제 API" , description = "Authorization 헤더에 토큰을 넣어주세요")
    @DeleteMapping(path = "")
    public ApiResponse<String> deleteMemberProfileImage(
            @AuthenticationPrincipal UserDetails userDetails) {


        String username = userDetails.getUsername();


        try {

            memberProfileImageService.deleteFile(username);

            return ApiResponse.onSuccess("삭제에 성공하였습니다");

        } catch (Exception e) {
            log.error("파일 삭제 오류: {}", e.getMessage());
            return ApiResponse.onFailure("404","이미지를 찾을 수 없습니다",null);
        }
    }






    @Operation(summary = "프로필 사진 업데이트 API" , description = "Authorization 헤더에 토큰을 넣어주세요")
    @PutMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> updateMemberProfileImage(
            @RequestPart(value = "file") MultipartFile multipartFile,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {


        String username = userDetails.getUsername();
        String dirName = "회원 프로필 이미지";


        try {

            String url = memberProfileImageService.updateProfileImage(multipartFile, dirName, username);
            log.info("프로필 이미지 업데이트 완료: {}", url);

            return ApiResponse.onSuccess(url);

        } catch (Exception e) {

            log.error("프로필 이미지 업데이트 오류: {}", e.getMessage());

            return ApiResponse.onFailure("404","이미지를 찾을 수 없습니다",null);
        }
    }



}
