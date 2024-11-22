package trend.project.web.dto.memberDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class MemberProfileFindDTO {



    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class FindMemberUsernameWithPhoneNumbersRequestDTO{

        @Schema(description = "핸드폰 번호 입니다")
        String phoneNumber;

    }

    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class FindMemberUsernameResponseDTO{

        @Schema(description = "회원ID입니다")
        String username;

    }




    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class FindMemberUsernameWithEmailsRequestDTO{

        @Schema(description = "이메일 입니다")
        String email;

    }





    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class FindMemberPasswordRequestDTO{

        @Schema(description = "회원ID입니다")
        String username;

        @Schema(description = "회원 이름입니다")
        String name;

        @Schema(description = "회원 이메일입니다")
        String email;


    }

    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class FindMemberPasswordResponseDTO{

        @Schema(description = "회원 비밀번호 입니다")
        String password;

    }
}
