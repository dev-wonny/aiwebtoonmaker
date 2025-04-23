package com.webtoonmaker.api.chat.application.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketSender {
    /**
     * Spring WebSocket은 STOMP 프로토콜 위에서 작동하는데
     * SimpMessageSendingOperations 구현체 내부적으로 STOMP를 알아서 처리함
     */
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Test > StompChatController > @MessageMapping("/chat") 이 붙은 클라이언트들이 자동으로 메시지를 받음
     * 세션 ID나 커넥션 직접 안 잡아도 됨 ->직접 WebSocket 세션 추적, 사용자 매핑, 메시지 전송 로직 필요 없음
     */
    public void sendToChatRoom(String key, Object payload) {
        messagingTemplate.convertAndSend("/topic/chat/" + key, payload);
    }

    /**
     * Spring은 WebSocket 연결 시 user 정보까지 매핑해줄 수 있어서
     * 특정 사용자한테만 보내는 것도 쉽게 가능
     * 1:1 알림, 귓속말
     */
    public void sendToUser(String userId, Object payload) {
        messagingTemplate.convertAndSendToUser(userId, "/queue/messages", payload);
    }
}