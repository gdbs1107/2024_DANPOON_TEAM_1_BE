package trend.project.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trend.project.api.ApiResponse;
import trend.project.service.RankingService;
import trend.project.validation.annotation.CategoryNameValid;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
@Tag(name = "이달의 랭킹 집계 API")
public class RankingController {

    private final RankingService rankingService;

    @Operation(summary = "이달의 인기 랭킹 집계 api")
    @PostMapping("/ranking")
    public ApiResponse<String> calculateMonthlyRanking() {
        rankingService.calculateAndSaveMonthlyRanking();
        return ApiResponse.onSuccess("랭킹이 성공적으로 집계되었습니다");
    }


    @Operation(summary = "이달의 인기 랭킹 집계 api")
    @PostMapping("/ranking/{categoryName}")
    public ApiResponse<String> calculateMonthlyRanking(@PathVariable @CategoryNameValid String categoryName) {
        rankingService.calculateAndSaveMonthlyRankingByCategory(categoryName);
        return ApiResponse.onSuccess("랭킹이 성공적으로 집계되었습니다");
    }
}