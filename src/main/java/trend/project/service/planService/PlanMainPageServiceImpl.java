package trend.project.service.planService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.converter.PlanMainConverter;
import trend.project.domain.Plan;
import trend.project.repository.RankingRepository;
import trend.project.repository.planRepository.PlanRepository;
import trend.project.web.dto.planDTO.PlanMainPageDTO;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanMainPageServiceImpl implements PlanMainPageService {

    private final PlanRepository planRepository;
    private final RankingRepository rankingRepository;

    @Override
    public List<PlanMainPageDTO.PlanBannerResponseDTO> getPlanBanner() {

        List<Plan> topPlans = planRepository.findTop4ByOrderByLikesCountDesc();

        // Plan 엔티티를 PlanBannerResponseDTO로 변환
        List<PlanMainPageDTO.PlanBannerResponseDTO> planBanners = PlanMainConverter.PlanToPlanBannerDTO(topPlans);

        return planBanners;
    }


    @Override
    public List<PlanMainPageDTO.PlanRankingResponseDTO> getRanking(){


    }
}
