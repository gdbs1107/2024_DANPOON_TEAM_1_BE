package trend.project.config;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import trend.project.filter.CustomLogoutFilter;
import trend.project.filter.LoginFilter;
import trend.project.jwt.JWTFilter;
import trend.project.jwt.JWTUtil;
import trend.project.repository.RefreshRepository;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //AuthenticationManager Bean 등록, UsernamePasswordAuthenticationFilter에서 필요하기 때문에 생성자로 주입해
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(Arrays.asList(
                                "http://localhost:3000",
                                "https://www.yuhang.store:8080",
                                "https://goormyuhang.shop"
                        ));
                        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        // exposedHeaders에 중복 설정 제거하고, 두 개의 헤더를 노출
                        configuration.setExposedHeaders(Arrays.asList("Set-Cookie", "access", "Authorization"));

                        return configuration;
                    }
                }));




        http.csrf((auth)->auth.disable());

        http.formLogin((auth)->auth.disable());
        http.httpBasic((auth)->auth.disable());


        http.authorizeHttpRequests((auth)->auth
                .requestMatchers("/health").permitAll()
                .requestMatchers("/members/profiles/**","/mails/**").permitAll()
                .requestMatchers("members/join","/login","/companies/join").permitAll()
                .requestMatchers("/members/find-usernames/emails","/members/find-usernames/phoneNumbers","/companies/find-passwords","/companies/find-usernames","/members/find-passwords").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()// Swagger 관련 경로를 허용
                .requestMatchers(HttpMethod.GET, "/plans/detail/{planId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/plans/detail/{planId}/similar").permitAll()
                .requestMatchers(HttpMethod.GET, "/plans/{planId}/comments/").permitAll()
                .requestMatchers(HttpMethod.GET, "/plans/poster/{planId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/plans/banner/{planId}").permitAll()
                .requestMatchers("/plans/main/**","/plans/ranking","/plans/themes/**","plans/search/**","/test").permitAll()
                .requestMatchers("/members/search/{name}").permitAll()
                
                .anyRequest().authenticated());

        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil,refreshRepository), UsernamePasswordAuthenticationFilter.class);

        http
                .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepository), LogoutFilter.class);


        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
