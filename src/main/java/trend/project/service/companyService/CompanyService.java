package trend.project.service.companyService;

import trend.project.web.dto.CompanyJoinDTO;

public interface CompanyService {
    CompanyJoinDTO.CompanyJoinResponseDTO joinCompany(CompanyJoinDTO.CompanyJoinRequestDTO request);
}
