package trend.project.web.controller.planController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import trend.project.api.ApiResponse;
import trend.project.service.planService.PlanSearchService;
import trend.project.web.dto.planDTO.PlanMainPageDTO;

import java.util.List;

@RestController
@RequestMapping("/plans/search")
@RequiredArgsConstructor
@Tag(name = "기획서 검색 API")
public class PlanSearchController {

    private final PlanSearchService planSearchService;


    // 검색 API
    @GetMapping("")
    @Operation(summary = "게시글 검색창 API", description = "제목을 기반으로 검색합니다")
    public ApiResponse<List<PlanMainPageDTO.PlanSearchResponseDTO>> searchPlans(@RequestParam String searchContent){

        List<PlanMainPageDTO.PlanSearchResponseDTO> result = planSearchService.searchPlan(searchContent);

        return ApiResponse.onSuccess(result);
    }



    // 지역별 조회
    @GetMapping("/regions")
    @Operation(summary = "게시글 검색 지역별 조회 API")
    public void searchPlansByRegion(){

    }


    // 테마별 조회
    @GetMapping("/themes")
    @Operation(summary = "게시글 검색 테마별 조회 API")
    public void searchPlansByThemes(){

    }


    // 기간별 조회
    @GetMapping("/regions")
    @Operation(summary = "게시글 검색 기간별 조회 API")
    public void searchPlansByPeriod(){

    }


    // 가격별 조회 - 유료/무료
    @GetMapping("/prices")
    @Operation(summary = "게시글 검색 가격별 조회 API")
    public void searchPlansByPrice(){

    }


}
