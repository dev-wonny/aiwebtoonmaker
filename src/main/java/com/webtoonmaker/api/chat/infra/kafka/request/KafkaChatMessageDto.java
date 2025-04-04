package com.webtoonmaker.api.chat.infra.kafka.request;

import com.webtoonmaker.api.chat.domain.enums.MessageTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class KafkaChatMessageDto {
    private UUID chatMessageId;//메시지 고유 ID (PK)
    private UUID chatRoomId;//채팅방 ID (FK), 객체지향으로 할까 말까?
    private UUID senderId;  // 보낸 사람
    private String content; // 메시지 내용
    private MessageTypeEnum messageTypeEnum;

    @Builder
    public KafkaChatMessageDto(
        UUID chatMessageId
        , UUID chatRoomId
        , UUID senderId
        , String content
        , MessageTypeEnum messageTypeEnum
    ) {
        this.chatMessageId = chatMessageId;
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
        this.messageTypeEnum = messageTypeEnum;
    }

    public static KafkaChatMessageDto of(
        UUID chatMessageId
        , UUID chatRoomId
        , UUID senderId
        , String content
        , MessageTypeEnum messageTypeEnum
    ) {
        return KafkaChatMessageDto.builder()
            .chatMessageId(chatMessageId)
            .chatRoomId(chatRoomId)
            .senderId(senderId)
            .content(content)
            .messageTypeEnum(messageTypeEnum)
            .build();
    }
}
