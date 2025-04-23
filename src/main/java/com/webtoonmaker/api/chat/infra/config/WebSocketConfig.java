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
//            .setAllowedOrigins("*")// CORS 허용
            .setAllowedOriginPatterns("*")
            .withSockJS();// SockJS 지원
    }

    // 메시지 브로커 설정 (구독 채널: /topic)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지 구독 prefix
        // 서버 → 클라이언트 메세지 전송
        // prefix, 구독 경로
        // /topic -> broadcast 용 (채팅방 등 다수 사용자 대상)
        // /queue -> 1:1 개인 메시지용 (point-to-point)
        // localhost:8080/ws-chat/topic/chat/{roomId}
        registry.enableSimpleBroker("/topic", "/queue");

        // 메시지 전송 prefix
        // 클라이언트 → 서버 메시지 전송 prefix
        // 메시지 송신 경로 prefix
        registry.setApplicationDestinationPrefixes("/app", "/pub");

        // convertAndSendToUser 위한 prefix
        // 특정 사용자에게 보내기 위한 prefix (e.g. 1:1 알림, 귓속말)
        // localhost:8080/ws-chat/user/queue/messages
        registry.setUserDestinationPrefix("/user");
    }
}

