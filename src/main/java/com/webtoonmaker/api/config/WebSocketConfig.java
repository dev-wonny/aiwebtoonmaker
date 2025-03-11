package com.webtoonmaker.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // 메세지 받기: /topic
    // 메시지 전송: /app
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메시지 브로커 설정 (구독 채널: /topic)
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }


    // WebSocket 연결: /ws-chat
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket STOMP 연결 (SockJS 지원)
        registry.addEndpoint("/ws-chat").setAllowedOrigins("*").withSockJS();
    }
}

