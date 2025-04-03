package com.webtoonmaker.api.chat.infra.kafka;

import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageListener {

    @KafkaListener(topics = "chat-messages", groupId = "chat-group")
    public void listen(ChatMessageDto messageDto) {
        // 메시지 처리 로직 (예: WebSocket을 통해 클라이언트에 전송)
    }
}