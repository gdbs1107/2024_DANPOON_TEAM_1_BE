package trend.project.converter;

import trend.project.domain.Plan;
import trend.project.web.dto.planDTO.PlanMainPageDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PlanMainConverter {

    public static List<PlanMainPageDTO.PlanBannerResponseDTO> PlanToPlanBannerDTO(List<Plan> topPlans) {
        List<PlanMainPageDTO.PlanBannerResponseDTO> planBanners = topPlans.stream()
                .map(plan -> PlanMainPageDTO.PlanBannerResponseDTO.builder()
                        .title(plan.getTitle())
                        .name(plan.getMember().getNickname())
                        .likesCount(plan.getLikesCount())
                        .commentsCount(plan.getCommentCount())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .imageLink(plan.getPlanPosterImage().getImageLink())
                        .build())
                .collect(Collectors.toList());
        return planBanners;
    }
}