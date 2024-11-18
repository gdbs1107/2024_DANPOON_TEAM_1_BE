package trend.project.service.planService;

import org.springframework.stereotype.Service;
import trend.project.web.dto.planDTO.PlanDetailDTO;

import java.util.List;

@Service
public interface PlanService {
    
    PlanDetailDTO.PlanDetailResponseDTO getPlanDetail(Long planId);
}
