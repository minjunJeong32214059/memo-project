package com.memo.memo_project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        		.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            		// 1. 누구나 접근 가능한 페이지 설정 (회원가입, 로그인, 정적 리소스)
            		.requestMatchers("/user/signup", "/login", "/css/**", "/js/**").permitAll()
                // 2. 그 외 모든 페이지는 로그인 필요
            		.anyRequest().authenticated()
            )
            .formLogin(form -> form
            		.loginPage("/login") // 직접 제작한 로그인 페이지 주소
                .defaultSuccessUrl("/", true) // 로그인 성공 시 메인으로
                .permitAll() // 로그인 페이지 누구나 접속 가능
            )
            .logout(logout -> logout
            	    .logoutUrl("/logout") // 이 설정이 내부적으로 매처 역할을 대신
            	    .logoutSuccessUrl("/login")
            	    .invalidateHttpSession(true)
            	    .deleteCookies("JSESSIONID")
            	    .permitAll()
            	);
        		
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}