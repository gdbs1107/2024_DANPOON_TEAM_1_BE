package trend.project.web.dto.planDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

public class PlanDetailDTO {
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class PlanDetailResponseDTO {
        
        @Schema(description = "행사 제목 입니다")
        private String title;
        
        @Schema(description = "행사 카테고리 입니다")
        private String category;
        
        @Schema(description = "행사 시작 날짜 입니다")
        private LocalDate startDate;
        
        @Schema(description = "행사 종료 날짜 입니다")
        private LocalDate endDate;
        
        @Schema(description = "행사 대상 입니다")
        private String target;
        
        @Schema(description = "행사 가격 입니다")
        private int cost;
        
        @Schema(description = "행사 상세 설명 입니다")
        private String content;
        
        @Schema(description = "행사 예산 입니다")
        private int budget;
        
        @Schema(description = "게시글 좋아요 수 입니다")
        private int likesCount;
        
        @Schema(description = "현재 좋아요 여부입니다.")
        private Boolean checkLike;
        
        @Schema(description = "게시글 댓글 수 입니다")
        private int commentCount;
        
        @Schema(description = "게시글 북마크 수 입니다")
        private int bookmarkCount;
        
        @Schema(description = "행사 상태 입니다(대기 중, 진행중, 완료, 취소됨")
        private String status;
        
        @Schema(description = "포스터 이미지 주소입니다.")
        private String posterUrl;
        
        @Schema(description = "배너 이미지 주소입니다")
        private String bannerUrl;
        
        @Schema(description = "기획자 이름입니다")
        private String username;
        
        @Schema(description = "기획자 팔로워 수 입니다")
        private int followerCount;
        
        /** 축제 개최 장소 **/
        @Schema(description = "시/도")
        private String province;
        
        @Schema(description = "시/군/구")
        private String city;
        
        @Schema(description = "읍/면/동")
        private String town;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class SameProvinceOtherPlanResponseDTO {
        
        @Schema(description = "기획자 이름입니다.")
        private String name;
        
        @Schema(description = "기획서 제목입니다.")
        private String title;
        
        @Schema(description = "포스터 이미지 링크입니다.")
        private String imageLink;
    }
}
