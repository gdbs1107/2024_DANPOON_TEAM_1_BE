package trend.project.web.controller.memberController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import trend.project.validation.annotation.UsernameDuplicate;
import trend.project.web.dto.memberDTO.MemberProfileFixDTO;

@RestController
@RequestMapping("/members/profiles")
@RequiredArgsConstructor
@Tag(name = "회원정보 수정 API")
public class MemberProfileFixController {



    // 회원 username 수정
    @Operation(summary = "회원 username 수정 API")
    @PatchMapping("/usernames")
    public void patchUsernames(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam @UsernameDuplicate String newUsername) {

    }


    // 회원 비밀번호 수정
    @Operation(summary = "회원 비밀번호 수정 수정 API")
    @PatchMapping("/passwords")
    public void patchPasswords(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam String newPasswords) {

    }


    // 회원 이름 수정
    @Operation(summary = "회원 이름 수정 API")
    @PatchMapping("/names")
    public void patchNames(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam String newName) {

    }


    // 회원 휴대폰 번호 수정
    @Operation(summary = "회원휴대폰 번호 수정 API")
    @PatchMapping("/phone-numbers")
    public void patchPhoneNumbers(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam String newPhoneNumber) {

    }


    // 회원 이메일 수정
    @Operation(summary = "회원 이메일 수정 API")
    @PatchMapping("/emails")
    public void patchEmails(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam String newEmails) {

    }


    // 회원 지역 수정
    @Operation(summary = "회원 지역 수정 API")
    @PatchMapping("/address")
    public void patchAddress(@AuthenticationPrincipal UserDetails userDetails,
                             @RequestBody MemberProfileFixDTO.MemberProfileRequestDTO request) {



    }
}
