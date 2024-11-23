package trend.project.web.dto.memberDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberSearchDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberSearchResponseDTO{

        @Schema(description = "회원 name입니다")
        String name;

        @Schema(description = "회원 follower 숫자입니다")
        Integer followerCount;

        @Schema(description = "회원 프로필 이미지 링크입니다")
        String imageLink;

    }
}
