package trend.project.web.controller.planController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import trend.project.api.ApiResponse;
import trend.project.service.planService.PlanService;
import trend.project.web.dto.planDTO.PlanDTO;
import trend.project.web.dto.planDTO.PlanDetailDTO;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
@Tag(name = "기획서 API")
public class PlanController {
    
    private final PlanService planService;
    
    @Operation(summary = "기획서 생성 API", description = "해당 API는 게시글의 정보를 입력받아 생성합니다.")
    @PostMapping
    public ApiResponse<PlanDTO.PlanCreateResponseDTO> createPlan(@RequestBody PlanDTO.PlanCreateRequestDTO req,
                                                                 @AuthenticationPrincipal UserDetails user) {
        PlanDTO.PlanCreateResponseDTO res = planService.planCreate(req, user.getUsername());
        return ApiResponse.onSuccess(res);
    }
    
    @Operation(summary = "기획서 상세 조회 API", description = "해당 API는 게시글 상세 정보를 조회합니다.")
    @GetMapping("/{planId}")
    public ApiResponse<PlanDetailDTO.PlanDetailResponseDTO> getDetailPlan(@PathVariable Long planId) {
        PlanDetailDTO.PlanDetailResponseDTO planDetail = planService.getPlanDetail(planId);
        return ApiResponse.onSuccess(planDetail);
    }
    
    @Operation(summary = "기획서 수정 API", description = "해당 API는 특정 게시글의 정보를 수정합니다. 권한는 작성자에게 있습니다.")
    @PutMapping("/{planId}")
    public ApiResponse<PlanDTO.PlanUpdateResponseDTO> updatePlan(@RequestBody PlanDTO.PlanUpdateRequestDTO req,
                                                                 @PathVariable Long planId,
                                                                 @AuthenticationPrincipal UserDetails user) {
        PlanDTO.PlanUpdateResponseDTO res = planService.planUpdate(req, planId, user.getUsername());
        return ApiResponse.onSuccess(res);
    }
    
    @Operation(summary = "기획서 삭제 API", description = "해당 API는 특정 게시글을 삭제합니다. 권한는 작성자에게 있습니다.")
    @DeleteMapping("/{planId}")
    public ApiResponse<String> deletePlan(@PathVariable Long planId,
                                          @AuthenticationPrincipal UserDetails user) {
        planService.deletePlan(planId, user.getUsername());
        
        return ApiResponse.onSuccess("기획서가 삭제되었습니다.");
    }

}
