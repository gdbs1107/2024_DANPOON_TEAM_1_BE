package trend.project.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trend.project.api.ApiResponse;
import trend.project.service.SearchHistoryService;
import trend.project.web.dto.HistoryDTO;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
@Tag(name = "검색기록 API")
public class SearchHistoryController {


    private final SearchHistoryService searchHistoryService;


    @Operation(summary = "검색 기록 조회 API")
    @GetMapping("")
    public ApiResponse<List<HistoryDTO.HistoryResponseDTO>> getSearchHistory(@AuthenticationPrincipal UserDetails userDetails){

        List<HistoryDTO.HistoryResponseDTO> result = searchHistoryService.getHistory(userDetails.getUsername());

        return ApiResponse.onSuccess(result);
    }
}
