package trend.project.web.controller.planController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import trend.project.api.ApiResponse;
import trend.project.service.planService.PlanByCategoryService;
import trend.project.validation.annotation.CategoryNameValid;
import trend.project.web.dto.planDTO.PlanCategoryPageDTO;

import java.util.List;

@RestController
@RequestMapping("/plans/themes")
@RequiredArgsConstructor
@Tag(name = "카테고리 별 게시글 조회 API")
public class PlanByCategoryController {

    private final PlanByCategoryService planByCategoryService;


    // 메인 배너 조회 API
    @Operation(summary = "메인 배너 조회 API")
    @GetMapping("/banner/{categoryName}")
    public ApiResponse<List<PlanCategoryPageDTO.PlanCategoryBannerResponseDTO>> getBanner(@PathVariable @CategoryNameValid String categoryName){

        List<PlanCategoryPageDTO.PlanCategoryBannerResponseDTO> result = planByCategoryService.getPlanByCategoryBanner(categoryName);

        return ApiResponse.onSuccess(result);

    }



    // 최고 좋아요 조회 API
    @Operation(summary = "최고 좋아요 조회 API")
    @GetMapping("/Ranking/{categoryName}")
    public ApiResponse<List<PlanCategoryPageDTO.PlanCategoryRankingResponseDTO>> getRanking(@PathVariable @CategoryNameValid String categoryName){

        List<PlanCategoryPageDTO.PlanCategoryRankingResponseDTO> ranking = planByCategoryService.getRanking(categoryName);

        return ApiResponse.onSuccess(ranking);
    }



    // 전체 게시글 조회 API - 최신별로 조회
    @Operation(summary = "전체 게시글 조회 API - 최신별로 조회")
    @GetMapping("/update-date/{categoryName}")
    public ApiResponse<List<PlanCategoryPageDTO.PlanCategoryResponseDTO>> getPlansByUpdateDate(@PathVariable @CategoryNameValid String categoryName){

        List<PlanCategoryPageDTO.PlanCategoryResponseDTO> result = planByCategoryService.getPlan(categoryName);

        return ApiResponse.onSuccess(result);

    }



    // 전체 게시글 조회 API - 좋아요 갯수 순으로 조회
    @Operation(summary = "전체 게시글 조회 API - 좋아요 갯수 순으로 조회")
    @GetMapping("/like-count/{categoryName}")
    public ApiResponse<List<PlanCategoryPageDTO.PlanCategoryResponseDTO>> getPlansByLikeCount(@PathVariable @CategoryNameValid String categoryName){

        List<PlanCategoryPageDTO.PlanCategoryResponseDTO> result = planByCategoryService.getPlanByLikeCount(categoryName);

        return ApiResponse.onSuccess(result);

    }



    // 전체 게시글 조회 API - 지역별로 조회
    @Operation(summary = "전체 게시글 조회 API - 지역별로 조회")
    @GetMapping("/region/{categoryName}")
    public void getPlansByRegion(@PathVariable @CategoryNameValid String categoryName,
                                 @RequestParam String region){

    }

}
