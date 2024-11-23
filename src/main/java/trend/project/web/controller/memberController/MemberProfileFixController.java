package trend.project.web.controller.memberController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import trend.project.api.ApiResponse;
import trend.project.service.memberService.MemberProfileFixService;
import trend.project.validation.annotation.UsernameDuplicate;
import trend.project.web.dto.memberDTO.MemberProfileFixDTO;

@RestController
@RequestMapping("/members/profiles")
@RequiredArgsConstructor
@Tag(name = "회원정보 수정 API")
public class MemberProfileFixController {


    private final MemberProfileFixService memberProfileFixService;


    // 회원 username 수정
    @Operation(summary = "회원 username 수정 API")
    @PatchMapping("/usernames")
    public ApiResponse<String> patchUsernames(@AuthenticationPrincipal UserDetails userDetails,
                                      @RequestParam @UsernameDuplicate String newUsername) {

        String fixedUsername = memberProfileFixService.fixMemberUsername(userDetails.getUsername(), newUsername);

        return ApiResponse.onSuccess(fixedUsername);
    }


    // 회원 비밀번호 수정
    @Operation(summary = "회원 비밀번호 수정 수정 API")
    @PatchMapping("/passwords")
    public ApiResponse<String> patchPasswords(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam String newPasswords) {

        memberProfileFixService.fixPassword(userDetails.getUsername(), newPasswords);

        return ApiResponse.onSuccess("비밀번호가 성공적으로 수정되었습니다");

    }


    // 회원 이름 수정
    @Operation(summary = "회원 이름 수정 API")
    @PatchMapping("/names")
    public ApiResponse<String> patchNames(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam String newName) {

        String fixedName = memberProfileFixService.fixName(userDetails.getUsername(), newName);

        return ApiResponse.onSuccess(fixedName);
    }


    // 회원 휴대폰 번호 수정
    @Operation(summary = "회원휴대폰 번호 수정 API")
    @PatchMapping("/phone-numbers")
    public ApiResponse<String> patchPhoneNumbers(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam String newPhoneNumber) {

        String fixedPhoneNumber = memberProfileFixService.fixPhoneNumber(userDetails.getUsername(), newPhoneNumber);

        return ApiResponse.onSuccess(fixedPhoneNumber);
    }


    // 회원 이메일 수정
    @Operation(summary = "회원 이메일 수정 API")
    @PatchMapping("/emails")
    public ApiResponse<String> patchEmails(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam String newEmails) {

        String fixedEmail = memberProfileFixService.fixEmail(userDetails.getUsername(), newEmails);

        return ApiResponse.onSuccess(fixedEmail);

    }


    // 회원 지역 수정
    @Operation(summary = "회원 지역 수정 API")
    @PatchMapping("/address")
    public ApiResponse<String> patchAddress(@AuthenticationPrincipal UserDetails userDetails,
                             @RequestBody MemberProfileFixDTO.MemberProfileRequestDTO request) {

        memberProfileFixService.fixAddress(userDetails.getUsername(), request);

        return ApiResponse.onSuccess("주소가 성공적으로 수정되었습니다");
    }
}
