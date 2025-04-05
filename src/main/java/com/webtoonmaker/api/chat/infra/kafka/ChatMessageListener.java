package com.webtoonmaker.api.chat.infra.kafka;

import com.webtoonmaker.api.chat.application.websocket.WebSocketSender;
import com.webtoonmaker.api.chat.infra.kafka.request.KafkaChatMessageDto;
import com.webtoonmaker.api.chat.infra.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageListener {
    private final JsonConverter jsonConverter;
    private final WebSocketSender webSocketSender;

    @KafkaListener(topics = "chat-messages", groupId = "chat-server-group")
    public void listen(String payload) {
        KafkaChatMessageDto message = jsonConverter.fromJson(payload, KafkaChatMessageDto.class);

        // TODO: Redis에서 유저 세션 찾기
        // TODO: WebSocket 전송 로직 수행
        webSocketSender.sendToChatRoom(String.valueOf(message.getChatRoomId()), message);

        System.out.println("🔔 Kafka 메시지 수신: " + message);
    }
}