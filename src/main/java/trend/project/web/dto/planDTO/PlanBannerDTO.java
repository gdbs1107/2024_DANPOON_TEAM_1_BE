package trend.project.web.dto.planDTO;

import lombok.*;

import java.time.LocalDate;

public class PlanBannerDTO {

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

}
