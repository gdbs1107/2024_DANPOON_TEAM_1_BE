package trend.project.service.planService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.domain.Plan;
import trend.project.domain.enumClass.Category;
import trend.project.repository.planRepository.PlanRepository;
import trend.project.web.dto.planDTO.PlanMainPageDTO;
import trend.project.web.dto.planDTO.PlanSearchDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanSearchServiceImpl implements PlanSearchService {


    private final PlanRepository planRepository;


    @Override
    public List<PlanMainPageDTO.PlanSearchResponseDTO> searchPlan(String title){

        List<Plan> searchPlans = planRepository.findTop4ByTitleContainingIgnoreCaseOrderByLikesCountDesc(title);

        List<PlanMainPageDTO.PlanSearchResponseDTO> searchResponse = searchPlans.stream()
                .map(plan -> PlanMainPageDTO.PlanSearchResponseDTO.builder()
                        .title(plan.getTitle())
                        .name(plan.getMember().getName())
                        .planImageLink(plan.getPlanPosterImage().getImageLink())
                        .build())
                .collect(Collectors.toList());

        return searchResponse;
    }


    @Override
    public List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByRegion(String title, String province){

        List<Plan> plans = planRepository.findByTitleContainingIgnoreCaseAndLocationProvince(title, province);

        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = plans.stream()
                .map(plan -> PlanSearchDTO.PlanMainSearchResponseDTO.builder()
                        .imageLink(plan.getPlanPosterImage().getImageLink())
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
                        .imageLink(plan.getPlanPosterImage().getImageLink())
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
                        .imageLink(plan.getPlanPosterImage().getImageLink())
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
                        .imageLink(plan.getPlanPosterImage().getImageLink())
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
                        .imageLink(plan.getPlanPosterImage().getImageLink())
                        .category(plan.getCategory())
                        .title(plan.getTitle())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .name(plan.getMember().getName())
                        .build())
                .collect(Collectors.toList());

        return result;
    }

}
