package trend.project.service.authService;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trend.project.domain.Company;
import trend.project.domain.Member;
import trend.project.repository.CompanyRepository;
import trend.project.repository.MemberRepository;
import trend.project.web.dto.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Try to find Member
        Member member = memberRepository.findByUsername(username).orElse(null);

        if (member != null) {
            return new CustomUserDetails(member);
        }

        // If Member not found, try to find Company
        Company company = companyRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new CustomUserDetails(company);

    }
}