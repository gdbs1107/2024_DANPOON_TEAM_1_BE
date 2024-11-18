package trend.project.service.companyService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.domain.Address;
import trend.project.domain.Company;
import trend.project.domain.Role;
import trend.project.domain.Status;
import trend.project.repository.AddressRepository;
import trend.project.repository.CompanyRepository;
import trend.project.web.dto.CompanyJoinDTO;

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

        Address newAddress = Address.builder()
                .province(request.getProvince())  // 시도
                .city(request.getCity())
                .member(null)
                .company(savedCompany)          // 주소를 회원과 연결
                .build();

        addressRepository.save(newAddress);

        return CompanyJoinDTO.CompanyJoinResponseDTO.builder()
                .companyId(savedCompany.getId())
                .build();
    }










    // username 중복 검사 메서드
    public void duplicateUsername(String username) {
        if (companyRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username is already in use");
        }
    }

    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }


}
