package trend.project.web.controller.planController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trend.project.api.ApiResponse;
import trend.project.service.planService.PlanDetailServiceImpl;
import trend.project.web.dto.planDTO.PlanDetailDTO;

import java.util.List;

@RestController
@RequestMapping("/plans/detail")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "기획서 상세 조회 페이지 API")
public class PlanDetailController {
    
    private final PlanDetailServiceImpl planDetailService;
    
    @Operation(summary = "기획서 상세 조회 API", description = "해당 API는 게시글 상세 정보를 조회합니다.")
    @GetMapping("/{planId}")
    public ApiResponse<PlanDetailDTO.PlanDetailResponseDTO> getDetailPlan(@PathVariable Long planId,
                                                                          @AuthenticationPrincipal UserDetails user) {
        String username = user != null ? user.getUsername() : null;
        PlanDetailDTO.PlanDetailResponseDTO planDetail = planDetailService.getPlanDetail(planId, username);
        return ApiResponse.onSuccess(planDetail);
    }
    
    @Operation(summary = "같은 지역 기획서 조회 API", description = "해당 API는 조회 기획서와 같은 지역의 다른 기획서를 조회합니다.")
    @GetMapping("/{planId}/similar")
    public ApiResponse<List<PlanDetailDTO.SameProvinceOtherPlanResponseDTO>> getSimilarPlan(@PathVariable Long planId) {
        List<PlanDetailDTO.SameProvinceOtherPlanResponseDTO> planResponseDTOS = planDetailService.getSameProvinceOtherPlans(planId);
        return ApiResponse.onSuccess(planResponseDTOS);
    }
}
