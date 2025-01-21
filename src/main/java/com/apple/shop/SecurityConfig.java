package com.apple.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CsrfTokenRepository csrfTokenRepository() {
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//        repository.setHeaderName("X-XSRF-TOKEN");
//        return repository;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // FilterChain, 모든 유저의 요청과 서버의 응답 사이에 자동으로 실행해주고 싶은 코드 담는 곳
          http.csrf((csrf)-> csrf.disable()); //csrf-> 다른 사이트에서 api 요청할 수 있는 기능, 이를 막음(disable)
//        http.csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository())
//                .ignoringRequestMatchers("/login")
//        );

        // JWT 입장권을 직접 fetch()의 headers:{}에 넣어서 보내면 CSRF 문제 해결 가능해서 보통 이걸 사용함
        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll() // 특정 페이지에 대해 로그인 검사 할지 결정가능
        );                                                     // permitAll()-> 검사 x, "/**"-> 모든 url
        // form 으로 login 하기 위해 필요한 코드
        http.formLogin((formLogin) -> formLogin.loginPage("/login") // form url
                .defaultSuccessUrl("/my-page") // 성공시 이동할 url
                .failureUrl("/fail") // 실패시 이동할 url
        );

        return http.build();

    }


}