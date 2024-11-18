package trend.project.web.dto.companyDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class CompanyProfileFindDTO {



    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class CompanyUsernameRequestDTO {

        @Schema(description = "담당자 이름 입니다")
        String name;

        @Schema(description = "회사명 입니다")
        String companyName;

        @Schema(description = "회사명 입니다<br>"+
        "핸드폰 번호에 대한 인증 외부 API 필요")
        String phoneNumber;


    }




    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class CompanyPasswordRequestDTO {

        @Schema(description = "회사명 입니다")
        String companyName;

        @Schema(description = "담당자 이름 입니다")
        String name;

        @Schema(description = "회사 이메일 입니다")
        String email;

        @Schema(description = "새로운 비밀번호 입니다")
        String password;
    }




    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class CompanyUsernameResponseDTO {

        @Schema(description = "username 입니다")
        String username;

    }



    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class CompanyPasswordResponseDTO {

        @Schema(description = "새로운 password 입니다")
        String password;

    }

}
