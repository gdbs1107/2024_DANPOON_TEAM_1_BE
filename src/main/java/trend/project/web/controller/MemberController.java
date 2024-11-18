package trend.project.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import trend.project.api.ApiResponse;
import trend.project.service.memberService.MemberService;
import trend.project.web.dto.memberDTO.MemberJoinDTO;
import trend.project.web.dto.memberDTO.MemberProfileFindDTO;
import trend.project.web.dto.memberDTO.MemberProfileUpdateDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "개인 정보 관리 API")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입 API")
    @PostMapping("/join")
    public ApiResponse<MemberJoinDTO.MemberJoinResponseDTO> join(@Valid @RequestBody MemberJoinDTO.MemberJoinRequestDTO request) {

        MemberJoinDTO.MemberJoinResponseDTO response = memberService.joinMember(request);

        return ApiResponse.onSuccess(response);
    }


    //프로필 조회 API
    @Operation(summary = "회원 프로필 조회 API")
    @GetMapping("/profiles")
    public void getProfiles(@AuthenticationPrincipal UserDetails userDetails){

    }



    // 회원 프로필 수정 API
    @Operation(summary = "회원프로필 수정 API")
    @PatchMapping("/profiles")
    public void updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestBody MemberProfileUpdateDTO.MemberProfileUpdateRequestDTO request){


    }



    // 회원 탈퇴 API
    @Operation(summary = "회원 탈퇴 API")
    @PatchMapping("")
    public ApiResponse<String> deleteMember(@AuthenticationPrincipal UserDetails userDetails){

        memberService.deleteMember(userDetails.getUsername());
        return ApiResponse.onSuccess("성공적으로 삭제 되었습니다");

    }



    // 아이디 찾기 - 휴대폰 번호로 찾기 (인증 외부 API 필요)
    @Operation(summary = "휴대폰 번호로 아이디 찾기 API")
    @GetMapping("/find-usernames/phoneNumbers")
    public ApiResponse<MemberProfileFindDTO.FindMemberUsernameResponseDTO> getUsernamesWithPhone(MemberProfileFindDTO.FindMemberUsernameWithPhoneNumbersRequestDTO request){

        MemberProfileFindDTO.FindMemberUsernameResponseDTO result = memberService.getUsernamesWithPhone(request);

        return ApiResponse.onSuccess(result);

    }

    // 아이디 찾기 - 이메일로 찾기 (인증 외부 API 필요)
    @Operation(summary = "이메일로 아이디 찾기 API")
    @GetMapping("/find-usernames/emails")
    public ApiResponse<MemberProfileFindDTO.FindMemberUsernameResponseDTO> getUsernamesWithEmails(MemberProfileFindDTO.FindMemberUsernameWithEmailsRequestDTO request){

        MemberProfileFindDTO.FindMemberUsernameResponseDTO result = memberService.getUsernamesWithEmail(request);

        return ApiResponse.onSuccess(result);

    }



    // 비밀번호 찾기 - 아이디,이름,이메일로 찾기
    @Operation(summary = "비밀번호 찾기 API")
    @GetMapping("/find-passwords")
    public ApiResponse<MemberProfileFindDTO.FindMemberPasswordResponseDTO> getPasswords(MemberProfileFindDTO.FindMemberPasswordRequestDTO request){

        MemberProfileFindDTO.FindMemberPasswordResponseDTO password = memberService.getPassword(request);

        return ApiResponse.onSuccess(password);
    }


}
