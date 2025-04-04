package com.webtoonmaker.api.chat.application.service;

import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import com.webtoonmaker.api.chat.infra.kafka.ChatKafkaProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChatService {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    private final ChatKafkaProducer chatKafkaProducer;

    public ChatService(ChatRoomService chatRoomService, ChatMessageService chatMessageService, ChatKafkaProducer chatKafkaProducer) {
        this.chatRoomService = chatRoomService;
        this.chatMessageService = chatMessageService;
        this.chatKafkaProducer = chatKafkaProducer;
    }

    @Transactional
    public void sendMessage(ChatMessageDto dto) {

        //채팅방 존재하는지 체크 -> ChatRoomService

        // 채팅 메세지 생성 -> ChatMessageService

        // 카프카 메세지 발행 -> chatKafkaProducer

        chatKafkaProducer.sendMessage("chat-messages", dto.getChatRoomId().toString(), dto.toKafka().toString());
    }
}
