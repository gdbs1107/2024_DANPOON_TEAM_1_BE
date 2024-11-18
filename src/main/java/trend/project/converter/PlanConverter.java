package trend.project.converter;

import trend.project.domain.*;
import trend.project.web.dto.planDTO.PlanDetailDTO;

public class PlanConverter {
    
    public static PlanDetailDTO.PlanDetailResponseDTO toPlanDetailResponseDTO(Plan plan,
                                                                              Member member,
                                                                              Location location,
                                                                              PlanPosterImage posterImage,
                                                                              PlanBannerImage bannerImage) {
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
                .commentCount(plan.getCommentCount())
                .bookmarkCount(plan.getBookmarkCount())
                .status(plan.getStatus().name())
                .posterUrl(posterImage.getImageLink())
                .bannerUrl(bannerImage.getImageLink())
                .username(member.getNickname())
                .followerCount(member.getFollowerCount())
                .province(location.getProvince())
                .city(location.getCity())
                .town(location.getTown())
                .build();
    }
    

}
