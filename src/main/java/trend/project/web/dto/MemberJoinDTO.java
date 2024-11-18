package trend.project.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import trend.project.validation.annotation.UsernameDuplicate;

public class MemberJoinDTO {

    @Getter
    public static class MemberJoinRequestDTO{


        @Schema(description = "회원이름 입니다")
        String name;

        @Pattern(regexp = "^\\d{11}$", message = "핸드폰 번호는 11자리 숫자만 가능합니다.")
        @Schema(description = "회원전화번호 입니다")
        String phoneNumber;

        @Email(message = "올바른 형식의 이메일을 입력해주세요")
        @Schema(description = "회원이메일 입니다")
        String email;

        @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "사용자 이름은 6~20자의 영문자와 숫자만 포함해야 합니다.")
        @UsernameDuplicate
        @Schema(description = "ID입니다 <br> 6~20자의 영문,숫자")
        String username;

        @Schema(description = "비밀번호는 8~12자의 영문, 숫자, 특수문자를 포함해야 합니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[a-zA-Z\\d!@#$%^&*(),.?\":{}|<>]{8,12}$")
        String password;

        @Schema(description = "회원 닉네임 입니다")
        String nickname;


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
    public static class MemberJoinResponseDTO{

        Long MemberId;

    }


}


