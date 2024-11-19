package trend.project.web.dto.planDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

public class PlanMainPageDTO {

    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class PlanBannerResponseDTO{

        String title;

        String name;

        Integer likesCount;

        Integer commentsCount;

        LocalDate startDate;

        LocalDate endDate;

    }


    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class PlanRankingResponseDTO{


        @Schema(description = "게시글 제목 입니다")
        private String title;

        @Schema(description = "게시글 작성자 닉네임 입니다")
        private String name;

        @Schema(description = "게시글 좋아요 수 입니다")
        private Integer likesCount;

        @Schema(description = "게시글 댓글 수 입니다")
        private Integer commentsCount;

    }

}
