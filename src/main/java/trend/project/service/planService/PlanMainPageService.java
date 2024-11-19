package trend.project.service.planService;

import org.springframework.stereotype.Service;
import trend.project.web.dto.planDTO.PlanBannerDTO;

import java.util.List;

@Service
public interface PlanMainPageService {
    List<PlanBannerDTO.PlanBannerResponseDTO> getPlanBanner();
}
