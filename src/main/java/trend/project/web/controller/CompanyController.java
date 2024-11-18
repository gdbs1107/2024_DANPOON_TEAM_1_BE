package trend.project.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trend.project.api.ApiResponse;
import trend.project.service.companyService.CompanyService;
import trend.project.web.dto.CompanyJoinDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
@Tag(name = "기업 정보 관리 API")
public class CompanyController {

    private final CompanyService companyService;

    @Operation(summary = "기업 회원가입 API")
    @PostMapping("/join")
    public ApiResponse<CompanyJoinDTO.CompanyJoinResponseDTO> joinCompany(@RequestBody @Valid CompanyJoinDTO.CompanyJoinRequestDTO request) {

        CompanyJoinDTO.CompanyJoinResponseDTO responseDTO = companyService.joinCompany(request);

        return ApiResponse.onSuccess(responseDTO);
    }
}
