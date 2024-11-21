package trend.project.web.dto.planDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

public class PlanSearchDTO {


    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class PlanMainSearchResponseDTO{

        @Schema(description = "포스터 이미지 링크 입니다")
        String imageLink;

        @Schema(description = "카테고리 이름 입니다")
        String category;

        @Schema(description = "기획서 제목 입니다")
        String title;

        @Schema(description = "시작 날짜 입니다")
        LocalDate startDate;

        @Schema(description = "종료 날짜 입니다")
        LocalDate endDate;

        @Schema(description = "기획서 제작자 이름 입니다")
        String name;


    }
}
