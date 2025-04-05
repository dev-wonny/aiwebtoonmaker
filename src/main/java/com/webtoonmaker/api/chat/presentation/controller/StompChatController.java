package com.webtoonmaker.api.chat.presentation.controller;

import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

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
@Controller
public class StompChatController {

    // 구독: JS: stompClient.subscribe("/topic/chat/{roomId}", callback)
    // 메세지 전송: JS: stompClient.send("/app/chat/{roomId}", {}, JSON.stringify(message)) -> @MessageMapping("/chat/{roomId}")
    // @SendTo("/topic/chat/{roomId}")-> 구독 중인 모든 클라이언트에게 전달
    @MessageMapping("/chat/{roomId}")
    //@SendTo: 프로토타입, 간단한 실시간 테스트 용도
    @SendTo("/topic/chat/{roomId}")
    public ChatMessageDto sendMessage(@DestinationVariable String roomId, ChatMessageDto message) {
        return message;
    }
}