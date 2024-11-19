package trend.project.service.planService;

import org.springframework.stereotype.Service;
import trend.project.web.dto.planDTO.PlanDTO;
import trend.project.web.dto.planDTO.PlanDetailDTO;

import java.util.List;

@Service
public interface PlanService {
    
    PlanDetailDTO.PlanDetailResponseDTO getPlanDetail(Long planId);
    
    PlanDTO.PlanCreateResponseDTO planCreate(PlanDTO.PlanCreateRequestDTO req, String username);
    
    PlanDTO.PlanUpdateResponseDTO planUpdate(PlanDTO.PlanUpdateRequestDTO req, Long planId, String username);
    
    void deletePlan(Long planId, String username);
}
