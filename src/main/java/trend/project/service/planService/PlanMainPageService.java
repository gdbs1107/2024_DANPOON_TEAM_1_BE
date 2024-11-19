package trend.project.service.planService;

import org.springframework.stereotype.Service;
import trend.project.web.dto.planDTO.PlanMainPageDTO;

import java.util.List;

@Service
public interface PlanMainPageService {
    List<PlanMainPageDTO.PlanBannerResponseDTO> getPlanBanner();
}
