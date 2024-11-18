package trend.project.web.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import trend.project.domain.Company;
import trend.project.domain.Member;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Member member;
    private final Company company;

    // Constructor overload to support both Member and Company
    public CustomUserDetails(Member member) {
        this.member = member;
        this.company = null;
    }

    public CustomUserDetails(Company company) {
        this.company = company;
        this.member = null;
    }

    // Role 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> {
            if (member != null) {
                return member.getRole().name();
            } else if (company != null) {
                return company.getRole().name();
            }
            return null;
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return member != null ? member.getPassword() : company.getPassword();
    }

    @Override
    public String getUsername() {
        return member != null ? member.getUsername() : company.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
