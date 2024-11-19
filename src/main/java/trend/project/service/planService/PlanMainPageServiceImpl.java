package trend.project.service.planService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.converter.PlanMainConverter;
import trend.project.domain.Plan;
import trend.project.domain.Ranking;
import trend.project.repository.RankingRepository;
import trend.project.repository.planRepository.PlanRepository;
import trend.project.web.dto.planDTO.PlanMainPageDTO;

import java.util.List;
import java.util.stream.Collectors;

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

        List<Ranking> rankings = rankingRepository.findAll();

        List<PlanMainPageDTO.PlanRankingResponseDTO> planRankings = rankings.stream()
                .map(ranking -> PlanMainPageDTO.PlanRankingResponseDTO.builder()
                        .title(ranking.getTitle())
                        .name(ranking.getName())
                        .likesCount(ranking.getLikesCount())
                        .commentsCount(ranking.getCommentsCount())
                        .imageLink(ranking.getImageLink())
                        .build())
                .collect(Collectors.toList());

        return planRankings;

    }


    @Override
    public List<PlanMainPageDTO.PlanMainResponseDTO> getHotties(){

        List<Plan> topPlans = planRepository.findTop5ByOrderByCommentCountDesc();

        List<PlanMainPageDTO.PlanMainResponseDTO> planHottiest = topPlans.stream()
                .map(plan -> PlanMainPageDTO.PlanMainResponseDTO.builder()
                        .title(plan.getTitle())
                        .name(plan.getMember().getName())
                        .town(plan.getLocation().getTown())
                        .imageLink(plan.getPlanPosterImage().getImageLink())
                        .build())
                .collect(Collectors.toList());

        return planHottiest;
    }


}
