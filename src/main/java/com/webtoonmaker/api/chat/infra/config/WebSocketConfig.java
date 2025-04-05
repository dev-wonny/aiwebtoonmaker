package com.webtoonmaker.api.chat.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //    private final StompHandler stompHandler;
//
//    public StompWebSocketConfig(StompHandler stompHandler) {
//        this.stompHandler = stompHandler;
//    }
    // 메세지 받기: /topic
    // 메시지 전송: /app
    // 메시지 브로커 설정 (/topic, /queue 등)

    // WebSocket STOMP 연결 (SockJS 지원)
    // WebSocket 연결: /ws-chat
    // WebSocket 핸드셰이크 엔드포인트 설정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws-chat")// 클라이언트가 연결할 endpoint
            .setAllowedOrigins("*")// CORS 허용
            .withSockJS();// SockJS 지원
    }

    // 메시지 브로커 설정 (구독 채널: /topic)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지 구독 prefix
        // 서버 → 클라이언트 전송 prefix, 구독 경로
        registry.enableSimpleBroker("/topic", "/queue");

        // 메시지 전송 prefix
        // 클라이언트 → 서버 전송 prefix, 메시지 송신 경로 prefix
        registry.setApplicationDestinationPrefixes("/app", "/pub");

        // convertAndSendToUser 위한 prefix
        registry.setUserDestinationPrefix("/user");
    }
}

