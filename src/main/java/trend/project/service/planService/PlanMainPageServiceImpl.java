package trend.project.service.planService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.converter.PlanMainConverter;
import trend.project.domain.Member;
import trend.project.domain.Plan;
import trend.project.domain.Ranking;
import trend.project.domain.enumClass.Category;
import trend.project.repository.MemberRepository;
import trend.project.repository.RankingRepository;
import trend.project.repository.planRepository.PlanRepository;
import trend.project.web.dto.planDTO.PlanMainPageDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanMainPageServiceImpl implements PlanMainPageService {

    private final PlanRepository planRepository;
    private final RankingRepository rankingRepository;
    private final MemberRepository memberRepository;

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



    @Override
    public List<PlanMainPageDTO.PlanCategoryResponseDTO> getTopPlanByCategory() {

        List<PlanMainPageDTO.PlanCategoryResponseDTO> topPlans = new ArrayList<>();

        for (Category category : Category.values()) {

            Plan topPlan = planRepository.findTopByCategoryOrderByLikesCountDesc(category);

            if (topPlan != null) {
                PlanMainPageDTO.PlanCategoryResponseDTO responseDTO = PlanMainPageDTO.PlanCategoryResponseDTO.builder()
                        .title(topPlan.getTitle()) // 제목
                        .category(topPlan.getCategory().name())
                        .likesCount(topPlan.getLikesCount())
                        .imageLink(topPlan.getPlanPosterImage() != null ? topPlan.getPlanPosterImage().getImageLink() : null) // 포스터 이미지 링크
                        .build();

                topPlans.add(responseDTO);
            }
        }
        return topPlans;
    }






    @Override
    public List<PlanMainPageDTO.PlanFavoriteMemberDTO> getPlanByPopularUsers(){

        Member popularMember = memberRepository.findTopByOrderByFollowerCountDesc();
        List<Plan> topPlans = planRepository.findTop5ByMember(popularMember);

        List<PlanMainPageDTO.PlanFavoriteMemberDTO> planPopular = topPlans.stream()
                .map(plan -> PlanMainPageDTO.PlanFavoriteMemberDTO.builder()
                        .title(plan.getTitle())
                        .name(plan.getMember().getName())
                        .followerCount(plan.getMember().getFollowerCount())
                        .memberImageLink(plan.getMember().getMemberProfileImages().isEmpty() ? null
                                : plan.getMember().getMemberProfileImages().get(0).getImageLink())
                        .town(plan.getLocation().getTown())
                        .likesCount(plan.getLikesCount())
                        .planImageLink(plan.getPlanPosterImage().getImageLink())
                        .build())
                .collect(Collectors.toList());

        return planPopular;


    }

    }

