package com.webtoonmaker.api.chat.controller;

import com.webtoonmaker.api.chat.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StompChatController {

    // 클라이언트가 /app/chat/{roomId}로 메시지를 보내면
    // /topic/chat/{roomId}를 구독한 클라이언트들이 메시지를 받음!
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/chat/{roomId}")
    public ChatMessage sendMessage(@DestinationVariable String roomId, ChatMessage message) {
        return message;  // /topic/chat/{roomId}에 메시지를 전송
    }
}
