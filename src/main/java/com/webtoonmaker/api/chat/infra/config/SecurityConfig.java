package com.webtoonmaker.api.chat.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)

            // Session 방식은 사용하지 않고 JWT 방식 사용
            .sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests((authorizeHttpRequests) ->
                    authorizeHttpRequests
//                    .requestMatchers("/users/v1/join").permitAll()
//                    .requestMatchers("/auth/v1/login").permitAll()
//                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/actuator/**").permitAll()
//                    .anyRequest().authenticated()
                        .anyRequest().permitAll() // 모든 요청 허용

            );

        // 필터 순서 설정
//            .addFilterBefore(commonApiHeaderFilter, JwtAuthenticationFilter.class)
//            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


//        http.exceptionHandling((eh) ->
//            eh.accessDeniedHandler(new AccessDeniedHandlerImpl())
//        );

        return http.build();
    }
}
