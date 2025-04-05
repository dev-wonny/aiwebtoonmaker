package com.webtoonmaker.api.chat.presentation.response;

import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import com.webtoonmaker.api.chat.domain.entity.ChatMessagesEntity;
import com.webtoonmaker.api.chat.domain.enums.MessageTypeEnum;
import com.webtoonmaker.api.chat.infra.kafka.request.KafkaChatMessageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ChatMessageResponseDto {
    private UUID chatMessageId;
    private UUID chatRoomId;
    private UUID senderId;
    private String content;
    private MessageTypeEnum messageTypeEnum;

    @Builder
    private ChatMessageResponseDto(UUID chatMessageId, UUID chatRoomId, UUID senderId, String content, MessageTypeEnum messageTypeEnum) {
        this.chatMessageId = chatMessageId;
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
        this.messageTypeEnum = messageTypeEnum;
    }

    public static ChatMessageResponseDto from(ChatMessageDto dto) {
        return ChatMessageResponseDto.builder()
            .chatMessageId(dto.getChatMessageId())
            .chatRoomId(dto.getChatRoomId())
            .senderId(dto.getSenderId())
            .content(dto.getContent())
            .messageTypeEnum(dto.getMessageTypeEnum())
            .build();
    }

    public static ChatMessageResponseDto fromMessage(ChatMessagesEntity entity) {
        return ChatMessageResponseDto.builder()
            .chatMessageId(entity.getChatMessageId())
            .chatRoomId(entity.getChatRoomId())
            .senderId(entity.getSenderId())
            .content(entity.getContent())
            .messageTypeEnum(entity.getMessageType())
            .build();
    }

    public KafkaChatMessageDto toKafka() {
        return KafkaChatMessageDto.of(
            this.getChatMessageId()
            , this.getChatRoomId()
            , this.getSenderId()
            , this.getContent()
            , this.messageTypeEnum
        );
    }
}