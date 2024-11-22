package trend.project.web.dto.planDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

public class PlanDTO {
    
    @Getter
    public static class PlanCreateRequestDTO {
        
        @Schema(description = "축제 이름")
        private String title;
        
        @Schema(description = "축제 카테고리")
        private Integer category;
        
        @Schema(description = "축제 시작일")
        private LocalDate startDate;
        
        @Schema(description = "축제 종료일")
        private LocalDate endDate;
        
        @Schema(description = "축제 대상")
        private String target;
        
        @Schema(description = "축제 참가 비용")
        private int cost;
        
        @Schema(description = "축제 티켓 구매 방식")
        private String bookingMethod;
        
        @Schema(description = "축제 내용")
        private String content;
        
        @Schema(description = "축제 예산")
        private int budget;
        
        /**
         * 축제 개최 장소
         **/
        @Schema(description = "시/도")
        private String province;
        
        @Schema(description = "시/군/구")
        private String city;
        
        @Schema(description = "읍/면/동")
        private String town;
        
    }
    
    @Getter
    public static class PlanUpdateRequestDTO {
        
        @Schema(description = "축제 이름")
        private String title;
        
        @Schema(description = "축제 카테고리")
        private Integer category;
        
        @Schema(description = "축제 시작일")
        private LocalDate startDate;
        
        @Schema(description = "축제 종료일")
        private LocalDate endDate;
        
        @Schema(description = "축제 대상")
        private String target;
        
        @Schema(description = "축제 참가 비용")
        private int cost;
        
        @Schema(description = "축제 티켓 구매 방식")
        private String bookingMethod;
        
        @Schema(description = "축제 내용")
        private String content;
        
        @Schema(description = "축제 예산")
        private int budget;
        
        /**
         * 축제 개최 장소
         **/
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
    public static class PlanCreateResponseDTO {
        
        Long planId;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class PlanUpdateResponseDTO {
        
        Long planId;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class PlanLikesCountResponseDTO {
        Integer likesCount;
        Boolean checkLike;
    }
}