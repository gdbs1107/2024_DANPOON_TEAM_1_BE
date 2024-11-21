package trend.project.web.controller.planController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import trend.project.api.ApiResponse;
import trend.project.service.planService.PlanSearchService;
import trend.project.web.dto.planDTO.PlanMainPageDTO;
import trend.project.web.dto.planDTO.PlanSearchDTO;

import java.util.List;

@RestController
@RequestMapping("/plans/search")
@RequiredArgsConstructor
@Tag(name = "기획서 검색 API")
public class PlanSearchController {

    private final PlanSearchService planSearchService;


    // 검색 API
    @GetMapping("{searchContent}")
    @Operation(summary = "게시글 검색창 API", description = "제목을 기반으로 검색합니다")
    public ApiResponse<List<PlanMainPageDTO.PlanSearchResponseDTO>> searchPlans(@PathVariable String searchContent){

        List<PlanMainPageDTO.PlanSearchResponseDTO> result = planSearchService.searchPlan(searchContent);

        return ApiResponse.onSuccess(result);
    }



    // 지역별 조회
    @GetMapping("/{searchContent}/regions")
    @Operation(summary = "게시글 검색 지역별 조회 API")
    public ApiResponse<List<PlanSearchDTO.PlanMainSearchResponseDTO>> searchPlansByRegion(@RequestParam String region,
                                                                                          @PathVariable String searchContent){

        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = planSearchService.searchPlanByRegion(region, searchContent);

        return ApiResponse.onSuccess(result);
    }


    // 테마별 조회
    @GetMapping("/{searchContent}/themes")
    @Operation(summary = "게시글 검색 테마별 조회 API")
    public ApiResponse<List<PlanSearchDTO.PlanMainSearchResponseDTO>> searchPlansByThemes(@RequestParam String category,
                                                                                          @PathVariable String searchContent){

        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = planSearchService.searchPlanByTheme(category, searchContent);

        return ApiResponse.onSuccess(result);

    }


    // 기간별 조회
    @GetMapping("/{searchContent}/regions")
    @Operation(summary = "게시글 검색 기간별 조회 API")
    public ApiResponse<List<PlanSearchDTO.PlanMainSearchResponseDTO>> searchPlansByPeriod(){

    }


    // 가격별 조회 - 유료/무료
    @GetMapping("/{searchContent}/prices")
    @Operation(summary = "게시글 검색 가격별 조회 API")
    public ApiResponse<List<PlanSearchDTO.PlanMainSearchResponseDTO>> searchPlansByPrice(){

    }


}
