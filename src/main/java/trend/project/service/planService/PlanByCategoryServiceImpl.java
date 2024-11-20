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




/*
    1. 카테고리 이름을 받음
    2. 카테고리와 일치 & 한달간 좋아요 집계가 가장 많은 다섯개의 기획서 검색
    3. 그리고 switch 문을 통해서 각 카테고리의 경우에 맞는 리파지토리에 저장
 */
    @Override
    public List<>





}
