package trend.project.web.controller.planController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import trend.project.api.ApiResponse;
import trend.project.service.planService.PlanSearchService;
import trend.project.validation.annotation.CategoryNameValid;
import trend.project.web.dto.planDTO.PlanMainPageDTO;
import trend.project.web.dto.planDTO.PlanSearchDTO;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/plans/search")
@RequiredArgsConstructor
@Tag(name = "기획서 검색 API")
@Slf4j
public class PlanSearchController {

    private final PlanSearchService planSearchService;


    // 검색 API
    @GetMapping("{searchContent}")
    @Operation(summary = "게시글 검색창 API", description = "제목을 기반으로 상위 네개의 게시글을 검색합니다")
    public ApiResponse<List<PlanMainPageDTO.PlanSearchResponseDTO>> searchPlans(@PathVariable String searchContent){

        List<PlanMainPageDTO.PlanSearchResponseDTO> result = planSearchService.searchPlan(searchContent);

        return ApiResponse.onSuccess(result);
    }


    @GetMapping("{searchContent}/all")
    @Operation(summary = "게시글 전체 검색 API", description = "제목을 기반으로 전체 검색합니다")
    public ApiResponse<List<PlanMainPageDTO.PlanSearchAllResponseDTO>> searchAllPlans(@PathVariable String searchContent){

        List<PlanMainPageDTO.PlanSearchAllResponseDTO> result = planSearchService.searchAllPlan(searchContent);

        return ApiResponse.onSuccess(result);
    }



    // 지역별 조회
    @GetMapping("/{searchContent}/regions")
    @Operation(summary = "게시글 검색 지역별 조회 API")
    public ApiResponse<List<PlanSearchDTO.PlanMainSearchResponseDTO>> searchPlansByRegion(@PathVariable String searchContent,
                                                                                          @RequestParam String region){

        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = planSearchService.searchPlanByRegion(searchContent,region);

        return ApiResponse.onSuccess(result);
    }


    // 테마별 조회
    @GetMapping("/{searchContent}/themes")
    @Operation(summary = "게시글 검색 테마별 조회 API")
    public ApiResponse<List<PlanSearchDTO.PlanMainSearchResponseDTO>> searchPlansByThemes(@PathVariable String searchContent,
                                                                                          @RequestParam @CategoryNameValid String category){
        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = planSearchService.searchPlanByTheme(searchContent, category);

        return ApiResponse.onSuccess(result);

    }


    @GetMapping("/{searchContent}/periods")
    @Operation(summary = "게시글 검색 기간별 조회 API")
    public ApiResponse<List<PlanSearchDTO.PlanMainSearchResponseDTO>> searchPlansByPeriod(@PathVariable String searchContent,
                                                                                          @RequestParam LocalDate startDate,
                                                                                          @RequestParam LocalDate endDate) {
        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = planSearchService.searchPlanByPeriod(searchContent, startDate, endDate);

        return ApiResponse.onSuccess(result);
    }


    // 가격별 조회 - 유료/무료
    @GetMapping("/{searchContent}/free")
    @Operation(summary = "게시글 검색 무료 게시글 조회 API")
    public ApiResponse<List<PlanSearchDTO.PlanMainSearchResponseDTO>> searchPlansByFree(@PathVariable String searchContent){

        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = planSearchService.searchPlanByFree(searchContent);

        return ApiResponse.onSuccess(result);

    }

    // 가격별 조회 - 유료/무료
    @GetMapping("/{searchContent}/non-free")
    @Operation(summary = "게시글 검색 유료 게시글 조회 API")
    public ApiResponse<List<PlanSearchDTO.PlanMainSearchResponseDTO>> searchPlansByNonFree(@PathVariable String searchContent){

        List<PlanSearchDTO.PlanMainSearchResponseDTO> result = planSearchService.searchPlanByNonFree(searchContent);

        return ApiResponse.onSuccess(result);

    }


}
