package trend.project.converter;

import trend.project.domain.*;
import trend.project.web.dto.planDTO.PlanDetailDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PlanDetailConverter {
    
    public static PlanDetailDTO.PlanDetailResponseDTO toPlanDetailResponseDTO(Plan plan,
                                                                              Member member,
                                                                              Location location,
                                                                              PlanPosterImage posterImage,
                                                                              PlanBannerImage bannerImage,
                                                                              Boolean checkLike) {
        return PlanDetailDTO.PlanDetailResponseDTO.builder()
                .title(plan.getTitle())
                .category(String.valueOf(plan.getCategory()))
                .startDate(plan.getStartDate())
                .endDate(plan.getEndDate())
                .target(plan.getTarget())
                .cost(plan.getCost())
                .content(plan.getContent())
                .budget(plan.getBudget())
                .likesCount(plan.getLikesCount())
                .checkLike(checkLike)
                .commentCount(plan.getCommentCount())
                .bookmarkCount(plan.getBookmarkCount())
                .status(plan.getStatus().name())
                .posterUrl(posterImage != null ? posterImage.getImageLink() : null)
                .bannerUrl(bannerImage != null ? bannerImage.getImageLink() : null)
                .username(member.getName())
                .followerCount(member.getFollowerCount())
                .province(location.getProvince())
                .city(location.getCity())
                .town(location.getTown())
                .build();
    }
    
    public static List<PlanDetailDTO.SameProvinceOtherPlanResponseDTO> toSameProvinceOtherPlanResponseDTOList(
            List<Plan> plans) {
        return plans.stream()
                .map(plan -> PlanDetailDTO.SameProvinceOtherPlanResponseDTO.builder()
                        .planId(plan.getId())
                        .imageLink(plan.getPlanPosterImage() != null ? plan.getPlanPosterImage().getImageLink() : null)
                        .title(plan.getTitle())
                        .name(plan.getMember().getName())
                        .build())
                .collect(Collectors.toList());
    }
    
}
