package com.webtoonmaker.api.chat.application.service;

import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import com.webtoonmaker.api.chat.infra.kafka.ChatMessageProducer;
import com.webtoonmaker.api.chat.infra.utils.JsonConverter;
import com.webtoonmaker.api.chat.presentation.response.ChatMessageResponseDto;
import com.webtoonmaker.api.chat.presentation.response.ChatRoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final ChatMessageProducer chatMessageProducer;
    private final JsonConverter jsonConverter;


    @Transactional
    public void sendMessage(ChatMessageDto dto) {
        //채팅방 존재하는지 체크 -> ChatRoomService
        ChatRoomResponseDto chatRoom = chatRoomService.getChatRoom(dto.getChatRoomId());

        //채팅 메세지 생성 -> ChatMessageService -> DB 저장
        //저장된 메시지를 반환받아서 Kafka로 전송하는 게 명확한 흐름
        ChatMessageResponseDto chatMessageResponseDto = chatMessageService.create(chatRoom.getChatRoomId(), dto);

        // 메세지 전파: 카프카 메세지 발행 -> chatKafkaProducer
        String jsonKafkaChatMessage = jsonConverter.toJson(chatMessageResponseDto.toKafka());
        chatMessageProducer.sendMessage("chat-messages", chatMessageResponseDto.getChatRoomId().toString(), jsonKafkaChatMessage);
    }
}
