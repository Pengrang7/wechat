package com.chat.demo.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

// import java.util.List;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//      @Bean
//      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//          http
//                  .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정
//                  .csrf(AbstractHttpConfigurer::disable) // CSRF 비활성화
//                  .authorizeHttpRequests(authz -> authz
//                          .requestMatchers("/users/register", "/users/login").permitAll() // 회원가입, 로그인 허용
//                          .anyRequest().authenticated()
//                  )
//                  .formLogin(AbstractHttpConfigurer::disable) // 기본 로그인 폼 비활성화
//                  .httpBasic(AbstractHttpConfigurer::disable); // HTTP Basic 인증 비활성화

//          return http.build();
//      }

//      // CORS 설정 추가
//      @Bean
//      public CorsConfigurationSource corsConfigurationSource() {
//          CorsConfiguration configuration = new CorsConfiguration();
//          configuration.setAllowedOrigins(List.of("http://localhost:3000")); // 프론트엔드 도메인
//          configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//          configuration.setAllowedHeaders(List.of("*"));
//          configuration.setAllowCredentials(true);

//          UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//          source.registerCorsConfiguration("/**", configuration);
//          return source;
//      }


// }

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 명확하게 추가
            .csrf(csrf -> csrf.disable()) // CSRF 비활성화
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/users/register", "/users/login").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.disable()) // 기본 로그인 비활성화
            .httpBasic(httpBasic -> httpBasic.disable()); // HTTP Basic 비활성화

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // 모든 출처 허용
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
