package trend.project.filter;



import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import trend.project.domain.RefreshEntity;
import trend.project.jwt.JWTUtil;
import trend.project.repository.RefreshRepository;
import trend.project.web.dto.memberDTO.MemberJoinDTO;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper 추가



    //login을 시작하는 메서드 -> UsernamePasswordAuthenticationFilter 에서 상속 받음
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            // JSON 요청 본문을 LoginRequestDto로 매핑
            MemberJoinDTO.MemberJoinRequestDTO loginRequestDto = objectMapper.readValue(request.getInputStream(), MemberJoinDTO.MemberJoinRequestDTO.class);

            String username = loginRequestDto.getUsername();
            String password = loginRequestDto.getPassword();

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

            // 인증 시도
            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        log.info("로그인 성공 토큰 작업 시작");

        // 유저 정보
        String username = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        GrantedAuthority auth = authorities.iterator().next();
        String role = auth.getAuthority();

        // 토큰 생성
        String access = jwtUtil.createJwt("access", username, role, 600000L); // 10분
        String refresh = jwtUtil.createJwt("refresh", username, role, 9990000000000L); // 24시간

        // 액세스 토큰 헤더에 설정
        response.setHeader("access", access);
        response.setHeader("refresh", refresh);


        /*// 리프레시 토큰 쿠키 추가
        createCookie(refresh, response);*/

        // Refresh 토큰 저장 로직
        addRefreshEntity(username, refresh, 9990000000000L);

        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        response.setStatus(401);
    }

    private void addRefreshEntity(String username, String refresh, Long expiredMs) {
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        log.info("Saving RefreshEntity: {}", refreshEntity);

        try {
            refreshRepository.save(refreshEntity);

            log.info("RefreshEntity saved successfully.");
        } catch (Exception e) {

            log.error("Failed to save RefreshEntity: {}", e.getMessage(), e);
        }
    }

    /*// 쿠키 생성
    private Cookie createCookie(String value, HttpServletResponse response) {

        // Cookie 객체 생성
        Cookie cookie = new Cookie("refresh", value);
        cookie.setMaxAge(24 * 60 * 60*60*60); // 유효 기간 설정
        cookie.setSecure(true); // HTTPS 요청에서만 쿠키 전송
        cookie.setPath("/"); // 쿠키 유효 경로 설정
        cookie.setHttpOnly(true); // JS 접근 방지

        // SameSite 속성 추가
        String cookieHeader = String.format("%s=%s; Path=%s; Max-Age=%d; Secure; HttpOnly; SameSite=None",
                cookie.getName(),
                cookie.getValue(),
                cookie.getPath(),
                cookie.getMaxAge()
        );
        response.addHeader("Set-Cookie", cookieHeader);

        return cookie; // 기존 Cookie 객체 반환
    }*/
}
