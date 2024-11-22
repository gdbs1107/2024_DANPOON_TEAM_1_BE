package trend.project.service.companyService;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.CompanyCategoryHandler;
import trend.project.domain.Address;
import trend.project.domain.Company;
import trend.project.domain.enumClass.Role;
import trend.project.domain.enumClass.Status;
import trend.project.repository.AddressRepository;
import trend.project.repository.CompanyRepository;
import trend.project.web.dto.CompanyJoinDTO;
import trend.project.web.dto.companyDTO.CompanyProfileFindDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AddressRepository addressRepository;


    @Override
    public CompanyJoinDTO.CompanyJoinResponseDTO joinCompany(CompanyJoinDTO.CompanyJoinRequestDTO request) {

        duplicateUsername(request.getUsername());

        Company company=Company.builder()
                .username(request.getUsername())
                .password(encodePassword(request.getPassword()))
                .companyName(request.getCompanyName())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .role(Role.ROLE_COM)
                .status(Status.ACTIVE)
                .build();

        Company savedCompany = companyRepository.save(company);

        return CompanyJoinDTO.CompanyJoinResponseDTO.builder()
                .companyId(savedCompany.getId())
                .build();
    }



    @Override
    public void deleteCompany(String username) {

        Company companyByUsername = getCompanyByUsername(username);
        companyByUsername.setInactive();  // 객체의 status 필드 수정

    }



    @Override
    public CompanyProfileFindDTO.CompanyUsernameResponseDTO getUsernames(CompanyProfileFindDTO.CompanyUsernameRequestDTO request) {


        Company findCompany = companyRepository.findByName(request.getName())
                .orElseThrow(() -> new CompanyCategoryHandler(ErrorStatus.COMPANY_NOT_FOUND));


        return CompanyProfileFindDTO.CompanyUsernameResponseDTO.builder()
                .username(findCompany.getUsername())
                .build();
    }


    @Override
    public CompanyProfileFindDTO.CompanyPasswordResponseDTO getPasswords(CompanyProfileFindDTO.CompanyPasswordRequestDTO request){

        Company byCompanyName = companyRepository.findByCompanyName(request.getCompanyName())
                .orElseThrow(() -> new CompanyCategoryHandler(ErrorStatus.COMPANY_NOT_FOUND));

        if (request.getPassword().equals(byCompanyName.getPassword())) {
            // 비밀번호 중복 오류
            throw new CompanyCategoryHandler(ErrorStatus.COMPANY_VALID_PASSWORD);
        }


        String newPassword = encodePassword(request.getPassword());

        byCompanyName.setPassword(newPassword);

        return CompanyProfileFindDTO.CompanyPasswordResponseDTO.builder()
                .password(byCompanyName.getPassword())
                .build();

    }












    // 매일 자정에 실행
    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void deleteOldInactiveCompanies() {

        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(30);  // 30일 이전 날짜 계산
        List<Company> inactiveCompaniesForDeletion = companyRepository.findInactiveCompaniesForDeletion(Status.INACTIVE, cutoffDate);

        // 30일 지난 회원 삭제
        companyRepository.deleteAll(inactiveCompaniesForDeletion);

    }



    // 회원 찾는 메서드
    public Company getCompanyByUsername(String username){
        return companyRepository.findByUsername(username)
                .orElseThrow(()-> new CompanyCategoryHandler(ErrorStatus.COMPANY_NOT_FOUND));
    }


    // username 중복 검사 메서드
    public void duplicateUsername(String username) {
        if (companyRepository.existsByUsername(username)) {
            throw new CompanyCategoryHandler(ErrorStatus.COMPANY_USERNAME_DUPLICATE);
        }
    }


    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}

