package trend.project.web.controller.companyController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import trend.project.api.ApiResponse;
import trend.project.service.companyService.CompanyProfileFixService;
import trend.project.service.companyService.CompanyService;

@RestController
@RequestMapping("/company/profile")
@RequiredArgsConstructor
@Tag(name = "회사 기본정보 수정 API")
public class CompanyProfileFixController {

    private final CompanyProfileFixService companyProfileFixService;



    // companyName 수정
    @Operation(summary = "회사명 수정 API")
    @PatchMapping("/company-names")
    public ApiResponse<String> fixCompanyNames(@AuthenticationPrincipal UserDetails userDetails,
                                       @RequestParam String newCompanyName){

        String newName = companyProfileFixService.fixCompanyName(userDetails.getUsername(), newCompanyName);

        return ApiResponse.onSuccess(newName);
    }


    // 담당자명 (name) 수정
    @Operation(summary = "담당자 명 수정 API")
    @PatchMapping("/names")
    public ApiResponse<String> fixNames(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String newName){

        String fixedName = companyProfileFixService.fixName(userDetails.getUsername(), newName);

        return ApiResponse.onSuccess(fixedName);

    }


    // 비밀번호 수정
    @Operation(summary = "비밀번호 수정 API")
    @PatchMapping("/passwords")
    public ApiResponse<String> fixPasswords(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String newPassword){

        companyProfileFixService.fixPassword(userDetails.getUsername(), newPassword);

        return ApiResponse.onSuccess("비밀번호가 성공적으로 수정되었습니다");
    }


    // phone number 수정
    @Operation(summary = "폰 번호 수정 API")
    @PatchMapping("/phone-numbers")
    public ApiResponse<String> fixPhoneNumbers(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String newPhoneNumber){

        String fixedPhoneNumber = companyProfileFixService.fixPhoneNumber(userDetails.getUsername(), newPhoneNumber);
        return ApiResponse.onSuccess(fixedPhoneNumber);
    }


    // username 수정
    @Operation(summary = "회사명 수정 API")
    @PatchMapping("/usernames")
    public ApiResponse<String> fixCUsernames(@AuthenticationPrincipal @Email UserDetails userDetails,
                                @RequestParam String newUsernames){

        String newUsername = companyProfileFixService.fixCompanyUsername(userDetails.getUsername(), newUsernames);
        return ApiResponse.onSuccess(newUsername);

    }

}
