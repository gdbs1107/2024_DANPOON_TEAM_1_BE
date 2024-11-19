package trend.project.service.planService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.domain.Plan;
import trend.project.repository.planRepository.PlanRepository;
import trend.project.web.dto.planDTO.PlanBannerDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanMainPageServiceImpl implements PlanMainPageService {

    private final PlanRepository planRepository;

    @Override
    public List<PlanBannerDTO.PlanBannerResponseDTO> getPlanBanner() {

        List<Plan> topPlans = planRepository.findTop4ByOrderByLikesCountDesc();

        // Plan 엔티티를 PlanBannerResponseDTO로 변환
        List<PlanBannerDTO.PlanBannerResponseDTO> planBanners = topPlans.stream()
                .map(plan -> PlanBannerDTO.PlanBannerResponseDTO.builder()
                        .title(plan.getTitle())
                        .name(plan.getMember().getName())
                        .likesCount(plan.getLikesCount())
                        .commentsCount(plan.getCommentCount())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .build())
                .collect(Collectors.toList());
        return planBanners;
    }
}
