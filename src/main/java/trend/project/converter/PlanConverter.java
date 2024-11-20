package trend.project.converter;

import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.PlanCategoryHandler;
import trend.project.domain.*;
import trend.project.domain.enumClass.Category;
import trend.project.domain.enumClass.PlanStatus;
import trend.project.web.dto.planDTO.PlanDTO;
import trend.project.web.dto.planDTO.PlanDetailDTO;

public class PlanConverter {
    
    public static Plan toCreatePlan(PlanDTO.PlanCreateRequestDTO planCreateRequestDTO) {
        
        if (planCreateRequestDTO.getStartDate().isAfter(planCreateRequestDTO.getEndDate())) {
            throw new PlanCategoryHandler(ErrorStatus.PlAN_END_DATE_INVALID);
        }
        
        Category category = switch (planCreateRequestDTO.getCategory()) {
            case 1 -> Category.MUSIC_PERFORMANCE;
            case 2 -> Category.ART_CRAFT;
            case 3 -> Category.LOCAL_CULTURE;
            case 4 -> Category.FOOD_MARKET;
            case 5 -> Category.TRADITION_HISTORY;
            case 6 -> Category.NATURE_AGRICULTURE;
            case 7 -> Category.SPORTS;
            case 8 -> Category.SEASONAL_EVENT;
            case 9 -> Category.COMMUNITY_FAMILY;
            default -> throw new PlanCategoryHandler(ErrorStatus.CATEGORY_INVALID);
        };
        
        return Plan.builder()
                .title(planCreateRequestDTO.getTitle())
                .category(category)
                .startDate(planCreateRequestDTO.getStartDate())
                .endDate(planCreateRequestDTO.getEndDate())
                .target(planCreateRequestDTO.getTarget())
                .cost(planCreateRequestDTO.getCost())
                .bookingMethod(planCreateRequestDTO.getBookingMethod())
                .content(planCreateRequestDTO.getContent())
                .budget(planCreateRequestDTO.getBudget())
                .status(PlanStatus.ON_HOLD)
                .build();
    }
    
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
                .posterUrl(posterImage != null ? posterImage.getImageLink() : null)
                .bannerUrl(bannerImage != null ? bannerImage.getImageLink() : null)
                .username(member.getName())
                .followerCount(member.getFollowerCount())
                .province(location.getProvince())
                .city(location.getCity())
                .town(location.getTown())
                .build();
    }
    

}
