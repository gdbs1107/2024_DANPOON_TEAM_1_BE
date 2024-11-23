package trend.project.service.companyService;

import org.springframework.stereotype.Service;

@Service
public interface CompanyProfileFixService {
    String fixCompanyUsername(String username, String newUsername);

    void fixPassword(String username, String newPassword);

    String fixName(String username, String newName);

    String fixCompanyName(String username, String newCompanyName);

    String fixPhoneNumber(String username, String newPhoneNumber);
}
