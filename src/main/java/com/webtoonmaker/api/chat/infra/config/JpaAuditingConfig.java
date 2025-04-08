package com.webtoonmaker.api.chat.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        // TODO: 실제 인증 사용자로 교체
        return () -> Optional.of("system"); // 인증 시스템이 없을 경우
    }
}