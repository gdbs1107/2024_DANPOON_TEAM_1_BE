package trend.project.service.companyService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.CompanyCategoryHandler;
import trend.project.domain.Company;
import trend.project.repository.CompanyRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyProfileFixServiceImpl implements CompanyProfileFixService {


    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public String fixCompanyUsername(String username, String newUsername){

        Company companyByUsername = getCompanyByUsername(username);
        companyByUsername.setUsername(newUsername);
        companyRepository.save(companyByUsername);

        return companyByUsername.getUsername();

    }


    @Override
    public void fixPassword(String username, String newPassword){

        Company companyByUsername = getCompanyByUsername(username);
        companyByUsername.setPassword(bCryptPasswordEncoder.encode(newPassword));
        companyRepository.save(companyByUsername);

    }


    @Override
    public String fixName(String username, String newName){
        Company companyByUsername = getCompanyByUsername(username);
        companyByUsername.setName(newName);
        companyRepository.save(companyByUsername);

        return companyByUsername.getName();
    }

    @Override
    public String fixCompanyName(String username, String newCompanyName){

        Company companyByUsername = getCompanyByUsername(username);
        companyByUsername.setCompanyName(newCompanyName);
        companyRepository.save(companyByUsername);

        return companyByUsername.getCompanyName();
    }


    @Override
    public String fixPhoneNumber(String username, String newPhoneNumber){

        Company companyByUsername = getCompanyByUsername(username);
        companyByUsername.setPhoneNumber(newPhoneNumber);
        companyRepository.save(companyByUsername);

        return companyByUsername.getPhoneNumber();
    }







    // 회원 찾는 메서드
    public Company getCompanyByUsername(String username){
        return companyRepository.findByUsername(username)
                .orElseThrow(()-> new CompanyCategoryHandler(ErrorStatus.COMPANY_NOT_FOUND));
    }


}
