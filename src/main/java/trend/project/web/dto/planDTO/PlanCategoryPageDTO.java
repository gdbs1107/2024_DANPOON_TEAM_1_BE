package trend.project.web.dto.planDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class PlanCategoryPageDTO {


    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class PlanCategoryBannerResponseDTO{

        @Schema(description = "게시글 제목 입니다")
        String title;

        @Schema(description = "게시글 작성자 닉네임 입니다")
        String name;

        @Schema(description = "게시글 좋아요 수 입니다")
        Integer likesCount;

        @Schema(description = "게시글 댓글 수 입니다")
        Integer commentsCount;

        @Schema(description = "포스터 이미지 링크입니다")
        String imageLink;


    }

    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class PlanCategoryRankingResponseDTO{


        @Schema(description = "게시글 제목 입니다")
        String title;

        @Schema(description = "게시글 작성자 닉네임 입니다")
        String name;

        @Schema(description = "게시글 좋아요 수 입니다")
        Integer likesCount;

        @Schema(description = "게시글 댓글 수 입니다")
        Integer commentsCount;

        @Schema(description = "포스터 이미지 링크입니다")
        String imageLink;


    }


    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class PlanCategoryResponseDTO{

        @Schema(description = "게시글 제목 입니다")
        String title;

        @Schema(description = "게시글 작성자 닉네임 입니다")
        String name;

        @Schema(description = "기획한 축제가 개최되는 장소입니다")
        String town;

        @Schema(description = "포스터 이미지 링크입니다")
        String imageLink;



    }
}
