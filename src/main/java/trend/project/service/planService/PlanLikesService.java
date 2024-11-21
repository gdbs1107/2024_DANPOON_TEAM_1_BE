package trend.project.service.planService;

import trend.project.web.dto.planDTO.PlanDTO;

public interface PlanLikesService {
    PlanDTO.PlanLikesCountResponseDTO togglePlanLike(Long planId, String username);
    
}
