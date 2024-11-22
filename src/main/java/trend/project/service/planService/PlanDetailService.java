package trend.project.service.planService;

import trend.project.web.dto.planDTO.PlanDetailDTO;

import java.util.List;

public interface PlanDetailService {
    
    PlanDetailDTO.PlanDetailResponseDTO getPlanDetail(Long planId, String username);
    
    List<PlanDetailDTO.SameProvinceOtherPlanResponseDTO> getSameProvinceOtherPlans(Long planId);
}
