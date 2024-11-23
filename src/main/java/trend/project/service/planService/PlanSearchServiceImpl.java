package trend.project.service.planService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.domain.Member;
import trend.project.domain.Plan;
import trend.project.domain.SearchHistory;
import trend.project.domain.enumClass.Category;
import trend.project.repository.MemberRepository;
import trend.project.repository.SearchHistoryRepository;
import trend.project.repository.planRepository.PlanRepository;
import trend.project.web.dto.planDTO.PlanMainPageDTO;
import trend.project.web.dto.planDTO.PlanSearchDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PlanSearchServiceImpl implements PlanSearchService {


    private final PlanRepository planRepository;
    private final SearchHistoryRepository searchHistoryRepository;
    private final MemberRepository memberRepository;



    @Override
    public List<PlanMainPageDTO.PlanSearchAllResponseDTO> searchAllPlan(String username, String title) {
        List<Plan> plans = planRepository.findAllByTitleContainingIgnoreCase(title);

        // 공통된 검색 결과 매핑 로직
        List<PlanMainPageDTO.PlanSearchAllResponseDTO> searchResponse = plans.stream()
                .map(plan -> PlanMainPageDTO.PlanSearchAllResponseDTO.builder()
                        .planId(plan.getId())
                        .title(plan.getTitle())
                        .name(plan.getMember().getName())
                        .planImageLink(getPlanImageLink(plan))
                        .category(plan.getCategory())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .build())
                .collect(Collectors.toList());

        // username이 null인 경우(익명 사용자)
        if (username == null) {
            return searchResponse; // 검색 기록 저장 없이 검색 결과만 반환
        }

        // username이 존재하는 경우
        Member findMember = memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다"));

        // 검색 기록 저장
        SearchHistory newSearchHistory = SearchHistory.builder()
                .searchContent(title)
                .member(findMember)
                .build();
        searchHistoryRepository.save(newSearchHistory);

        return searchResponse;
    }


    @Override
    public List<PlanMainPageDTO.PlanSearchResponseDTO> searchPlan(String title){


        List<Plan> searchPlans = planRepository.findTop4ByTitleContainingIgnoreCaseOrderByLikesCountDesc(title);

        List<PlanMainPageDTO.PlanSearchResponseDTO> searchResponse = searchPlans.stream()
                .map(plan -> PlanMainPageDTO.PlanSearchResponseDTO.builder()
                        .planId(plan.getId())
                        .title(plan.getTitle())
                        .name(plan.getMember().getName())
                        .planImageLink(getPlanImageLink(plan))
                        .build())
                .collect(Collectors.toList());



        return searchResponse;
    }
    
    
    @Override
    public List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByRegion(String title, String province){

        List<Plan> plans = planRepository.findByTitleContainingIgnoreCaseAndLocationProvince(title, province);

        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = plans.stream()
                .map(plan -> PlanSearchDTO.PlanMainSearchResponseDTO.builder()
                        .planId(plan.getId())
                        .imageLink(getPlanImageLink(plan))
                        .category(plan.getCategory())
                        .title(plan.getTitle())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .name(plan.getMember().getName())
                        .build())
                .collect(Collectors.toList());

        return result;
    }



    @Override
    public List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByTheme(String title, String category){

        List<Plan> plans = planRepository.findByTitleContainingIgnoreCaseAndCategory(title, Category.valueOf(category));

        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = plans.stream()
                .map(plan -> PlanSearchDTO.PlanMainSearchResponseDTO.builder()
                        .planId(plan.getId())
                        .imageLink(getPlanImageLink(plan))
                        .category(plan.getCategory())
                        .title(plan.getTitle())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .name(plan.getMember().getName())
                        .build())
                .collect(Collectors.toList());

        return result;
    }


    @Override
    public List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByFree(String title){

        List<Plan> plans = planRepository.findByTitleContainingIgnoreCaseAndBudget(title,0);

        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = plans.stream()
                .map(plan -> PlanSearchDTO.PlanMainSearchResponseDTO.builder()
                        .planId(plan.getId())
                        .imageLink(getPlanImageLink(plan))
                        .category(plan.getCategory())
                        .title(plan.getTitle())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .name(plan.getMember().getName())
                        .build())
                .collect(Collectors.toList());

        return result;
    }



    @Override
    public List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByNonFree(String title){

        List<Plan> plans = planRepository.findByTitleContainingIgnoreCaseAndBudgetGreaterThan(title,0);

        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = plans.stream()
                .map(plan -> PlanSearchDTO.PlanMainSearchResponseDTO.builder()
                        .planId(plan.getId())
                        .imageLink(getPlanImageLink(plan))
                        .category(plan.getCategory())
                        .title(plan.getTitle())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .name(plan.getMember().getName())
                        .build())
                .collect(Collectors.toList());

        return result;
    }


    @Override
    public List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByPeriod(String title,
                                                                            LocalDate startDate,
                                                                            LocalDate endDate){

        List<Plan> plans = planRepository.findPlansByTitleAndPeriod(title,startDate,endDate);

        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = plans.stream()
                .map(plan -> PlanSearchDTO.PlanMainSearchResponseDTO.builder()
                        .planId(plan.getId())
                        .imageLink(getPlanImageLink(plan))
                        .category(plan.getCategory())
                        .title(plan.getTitle())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .name(plan.getMember().getName())
                        .build())
                .collect(Collectors.toList());

        return result;
    }






    private static String getPlanImageLink(Plan plan) {
        return plan.getPlanPosterImage() != null ? plan.getPlanPosterImage().getImageLink() : null;
    }
}
