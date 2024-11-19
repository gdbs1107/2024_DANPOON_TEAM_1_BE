package trend.project.converter;

import trend.project.domain.Plan;
import trend.project.web.dto.planDTO.PlanBannerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PlanMainConverter {

    public static List<PlanBannerDTO.PlanBannerResponseDTO> PlanToPlanBannerDTO(List<Plan> topPlans) {
        List<PlanBannerDTO.PlanBannerResponseDTO> planBanners = topPlans.stream()
                .map(plan -> PlanBannerDTO.PlanBannerResponseDTO.builder()
                        .title(plan.getTitle())
                        .name(plan.getMember().getNickname())
                        .likesCount(plan.getLikesCount())
                        .commentsCount(plan.getCommentCount())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .build())
                .collect(Collectors.toList());
        return planBanners;
    }
}