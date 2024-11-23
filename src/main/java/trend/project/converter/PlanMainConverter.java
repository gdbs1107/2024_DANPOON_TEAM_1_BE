package trend.project.converter;

import trend.project.domain.Plan;
import trend.project.web.dto.planDTO.PlanMainPageDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PlanMainConverter {

    public static List<PlanMainPageDTO.PlanBannerResponseDTO> PlanToPlanBannerDTO(List<Plan> topPlans) {
        List<PlanMainPageDTO.PlanBannerResponseDTO> planBanners = topPlans.stream()
                .map(plan -> PlanMainPageDTO.PlanBannerResponseDTO.builder()
                        .planId(plan.getId())
                        .title(plan.getTitle())
                        .name(plan.getMember().getNickname())
                        .likesCount(plan.getLikesCount())
                        .commentsCount(plan.getCommentCount())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .imageLink(getImageLink(plan))
                        .build())
                .collect(Collectors.toList());
        return planBanners;
    }

    private static String getImageLink(Plan plan) {
        if (plan.getPlanBannerImages() == null || plan.getPlanBannerImages().isEmpty()) {
            return null; // null 또는 빈 리스트일 경우 null 반환
        }
        return plan.getPlanBannerImages().get(0).getImageLink();
    }
}