package com.webtoonmaker.api.chat.presentation.controller.test;

import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * STOMP 기반 채팅 브로드캐스트 컨트롤러
 * <p>
 * ✅ 용도:
 * - 간단한 실시간 메시지 브로드캐스팅
 * - 테스트/프로토타입용
 * <p>
 * ❌ 한계:
 * - 인증, 유저별 구독 관리 불가
 * - 세션 추적 및 고급 권한 처리 불가
 */
@Slf4j
@Controller
public class StompChatController {

    // 구독: JS: stompClient.subscribe("/topic/chat/{roomId}", callback)
    // 메세지 전송: JS: stompClient.send("/app/chat/{roomId}", {}, JSON.stringify(message)) -> @MessageMapping("/chat/{roomId}")
    // 브로드캐스팅: @SendTo("/topic/chat/{roomId}")-> 구독 중인 모든 클라이언트에게 전달
    @MessageMapping("/chat/{roomId}")
    //@SendTo: 프로토타입, 간단한 실시간 테스트 용도
    @SendTo("/topic/chat/{roomId}")
    public ChatMessageDto sendMessage(@DestinationVariable String roomId, ChatMessageDto message) {
        log.info("🔔 [TEST-STOMP] message received - roomId: {}, senderId: {}, content: {}", roomId, message.getSenderId(), message.getContent());

        // 메시지에 채팅방 ID 및 전송 시간 설정 (테스트용)
        message.setChatRoomId(UUID.fromString(roomId));
        message.setCreatedAt(LocalDateTime.now());
        return message;
    }
}