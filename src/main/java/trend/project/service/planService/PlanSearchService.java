package trend.project.service.planService;

import org.springframework.stereotype.Service;
import trend.project.web.dto.planDTO.PlanMainPageDTO;
import trend.project.web.dto.planDTO.PlanSearchDTO;

import java.time.LocalDate;
import java.util.List;

@Service
public interface PlanSearchService {
    List<PlanMainPageDTO.PlanSearchResponseDTO> searchPlan(String title);

    List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByRegion(String title, String province);

    List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByTheme(String title, String category);

    List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByFree(String title);

    List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByNonFree(String title);

    List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByPeriod(String title,
                                                                     LocalDate startDate,
                                                                     LocalDate endDate);
}
