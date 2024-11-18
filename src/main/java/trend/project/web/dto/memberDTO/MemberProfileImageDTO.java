package trend.project.web.dto.memberDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class MemberProfileImageDTO {


    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class MemberProfileImageResponseDTO{

        @Schema(description = "이미지 저장링크 입니다")
        String imageLink;
    }
}
