package trend.project.web.dto.companyDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public class CompanyJoinDTO {

    @Getter
    public static class CompanyJoinRequestDTO{


        @Email(message = "올바른 형식의 이메일을 입력해주세요")
        @Schema(description = "username 입니다 <br> +" +
                "기업은 회원가입 시, username을 이메일로 사용합니다")
        String username;

        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[a-zA-Z\\d!@#$%^&*(),.?\":{}|<>]{8,12}$",
                message = "비밀번호는 8~12자의 영문, 숫자, 특수문자를 포함해야 합니다.")
        String password;

        @Schema(description = "담당자 이름 입니다")
        String name;

        @Pattern(regexp = "^\\d{11}$", message = "핸드폰 번호는 11자리 숫자만 가능합니다.")
        @Schema(description = "회사 전화번호 입니다")
        String phoneNumber;

        @Schema(description = "회사명 입니다")
        String companyName;



        /* ---------- 주소 ---------- */
        @Schema(description = "시/도 입니다")
        String province;

        @Schema(description = "시/군/구 입니다")
        String city;

    }


    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class CompanyJoinResponseDTO{

        Long companyId;

    }
}
