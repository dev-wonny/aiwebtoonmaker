package com.webtoonmaker.api.chat.infra.kafka.request;

import com.webtoonmaker.api.chat.application.dto.BaseTimeDto;
import com.webtoonmaker.api.chat.domain.enums.MessageTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class KafkaChatMessageDto extends BaseTimeDto {
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
        , LocalDateTime createdAt
        , LocalDateTime updatedAt
    ) {
        this.chatMessageId = chatMessageId;
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
        this.messageTypeEnum = messageTypeEnum;
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    public static KafkaChatMessageDto of(
        UUID chatMessageId
        , UUID chatRoomId
        , UUID senderId
        , String content
        , MessageTypeEnum messageTypeEnum
        , LocalDateTime createdAt
        , LocalDateTime updatedAt
    ) {
        return KafkaChatMessageDto.builder()
            .chatMessageId(chatMessageId)
            .chatRoomId(chatRoomId)
            .senderId(senderId)
            .content(content)
            .messageTypeEnum(messageTypeEnum)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }
}
