package com.example.TripTailor.config;

import com.example.TripTailor.service.CustomOAuth2UserService;
import com.example.TripTailor.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailService;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // CSRF 비활성 (개발 단계용)
                //.csrf().disable()

                // URL 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                        // 정적 리소스 "/favicon-svg.svg"
                        .requestMatchers("/css/**", "/js/**", "/image/**").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // 로그인 필요x
                        .requestMatchers("/main", "/signup/**").permitAll()
                        // API 및 인증 관련
                        .requestMatchers("/api/auth/**", "/login", "/oauth2/**", "/login/oauth2/**").permitAll()
                        .requestMatchers("/oauth2-redirect.html", "/error", "/error/**").permitAll()
                        .anyRequest().authenticated()
                )

                // 폼 로그인 (로컬 로그인)
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login") // POST 요청 경로
                        .usernameParameter("loginId")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/main", true)
                        .permitAll()
                )

                // 소셜 로그인 (OAuth2)
                .oauth2Login(oauth -> oauth
                        .loginPage("/login") // 카카오 버튼도 같은 로그인 페이지에서
                        .userInfoEndpoint(user -> user.userService(customOAuth2UserService))
                        .defaultSuccessUrl("/main", true)
                )

                // 로그아웃
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/deleteaccount")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                )

                // 인증 서비스 연결
                .userDetailsService(customUserDetailService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
