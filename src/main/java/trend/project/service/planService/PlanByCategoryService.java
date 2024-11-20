package trend.project.service.planService;

import org.springframework.stereotype.Service;
import trend.project.web.dto.planDTO.PlanCategoryPageDTO;

import java.util.List;

@Service
public interface PlanByCategoryService {
    List<PlanCategoryPageDTO.PlanCategoryBannerResponseDTO> getPlanByCategoryBanner(String categoryName);
}
