package trend.project.web.controller.companyController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company/profile")
@Tag(name = "회사 기본정보 수정 API")
public class CompanyProfileFixController {



    // companyName 수정
    @Operation(summary = "회사명 수정 API")
    @PatchMapping("/company-names")
    public void fixCompanyNames(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String newCompanyName){

    }


    // 담당자명 (name) 수정
    @Operation(summary = "담당자 명 수정 API")
    @PatchMapping("/names")
    public void fixNames(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String newName){

    }


    // 비밀번호 수정
    @Operation(summary = "비밀번호 수정 API")
    @PatchMapping("/passwords")
    public void fixPasswords(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String newPassword){

    }


    // phone number 수정
    @Operation(summary = "폰 번호 수정 API")
    @PatchMapping("/phone-numbers")
    public void fixPhoneNumbers(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String newPhoneNumber){

    }


    // username 수정
    @Operation(summary = "회사명 수정 API")
    @PatchMapping("/usernames")
    public void fixCUsernames(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String newUsernames){

    }

}
