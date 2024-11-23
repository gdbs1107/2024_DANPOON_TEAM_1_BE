package trend.project.service.companyService;

import org.springframework.scheduling.annotation.Scheduled;
import trend.project.web.dto.CompanyJoinDTO;
import trend.project.web.dto.companyDTO.CompanyProfileFindDTO;

public interface CompanyService {
    CompanyJoinDTO.CompanyJoinResponseDTO joinCompany(CompanyJoinDTO.CompanyJoinRequestDTO request);

    void deleteCompany(String username);

    CompanyProfileFindDTO.CompanyUsernameResponseDTO getUsernames(CompanyProfileFindDTO.CompanyUsernameRequestDTO request);

    CompanyProfileFindDTO.CompanyPasswordResponseDTO getPasswords(CompanyProfileFindDTO.CompanyPasswordRequestDTO request);

    String rollBackDelete(String username);

    // 매일 자정에 실행
    @Scheduled(cron = "0 0 0 * * ?")
    void deleteOldInactiveCompanies();
}
