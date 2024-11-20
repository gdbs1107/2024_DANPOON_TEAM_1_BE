package trend.project.service.planService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.domain.Plan;
import trend.project.domain.enumClass.Category;
import trend.project.repository.planRepository.PlanRepository;
import trend.project.web.dto.planDTO.PlanCategoryPageDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanByCategoryServiceImpl implements PlanByCategoryService {

    private final PlanRepository planRepository;


    @Override
    public List<PlanCategoryPageDTO.PlanCategoryBannerResponseDTO> getPlanByCategoryBanner(String categoryName){


        List<Plan> byCategory = planRepository.findTop4ByCategoryOrderByLikesCountDesc(Category.valueOf(categoryName));

        List<PlanCategoryPageDTO.PlanCategoryBannerResponseDTO> planCategoryBanner = byCategory.stream()
                .map(plan -> PlanCategoryPageDTO.PlanCategoryBannerResponseDTO.builder()
                        .title(plan.getTitle())
                        .name(plan.getMember().getName())
                        .likesCount(plan.getLikesCount())
                        .commentsCount(plan.getCommentCount())
                        .imageLink(plan.getPlanPosterImage().getImageLink())
                        .build())
                .collect(Collectors.toList());

        return planCategoryBanner;
    }



    @Override
    public List<>





}
