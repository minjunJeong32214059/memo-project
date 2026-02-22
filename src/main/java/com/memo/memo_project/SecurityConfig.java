package com.memo.memo_project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // 모든 페이지는 로그인 필수
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
}