package trend.project.web.controller.planController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trend.project.api.ApiResponse;
import trend.project.service.planService.PlanLikesService;
import trend.project.web.dto.planDTO.PlanDTO;

@RestController
@RequestMapping("/plans/{planId}/likes")
@RequiredArgsConstructor
@Tag(name = "기획서 좋아요 API")
public class PlanLikesController {
    
    private final PlanLikesService planLikesService;
    
    @Operation(summary = "기획서 좋아요 API", description = "해당 API는 기획서를 토글 형식으로 좋아요를 합니다. <br><br>" +
            "Authorization 헤더에 토큰을 넣어주세요")
    @PostMapping("/")
    public ApiResponse<PlanDTO.PlanLikesCountResponseDTO> toggleLike(@PathVariable Long planId,
                                                                           @AuthenticationPrincipal UserDetails user) {
        PlanDTO.PlanLikesCountResponseDTO res = planLikesService.togglePlanLike(planId, user.getUsername());
        
        return ApiResponse.onSuccess(res);
    }
    
}
