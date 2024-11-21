package trend.project.service.planService;

import org.springframework.stereotype.Service;
import trend.project.web.dto.planDTO.PlanMainPageDTO;
import trend.project.web.dto.planDTO.PlanSearchDTO;

import java.util.List;

@Service
public interface PlanSearchService {
    List<PlanMainPageDTO.PlanSearchResponseDTO> searchPlan(String title);

    List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByRegion(String title, String province);

    List<PlanSearchDTO.PlanMainSearchResponseDTO> searchPlanByTheme(String title, String category);
}
