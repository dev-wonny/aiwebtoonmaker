package com.webtoonmaker.api.chat.application.dto;

import com.webtoonmaker.api.chat.domain.enums.MessageTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageDto extends BaseTimeDto {
    private UUID chatMessageId;//메시지 고유 ID (PK)
    private UUID chatRoomId;//채팅방 ID (FK), 객체지향으로 할까 말까?
    private UUID senderId;  // 보낸 사람
    private String content; // 메시지 내용
    private MessageTypeEnum messageTypeEnum;

    @Builder
    private ChatMessageDto(
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

    public static ChatMessageDto of(
        UUID chatMessageId
        , UUID chatRoomId
        , UUID senderId
        , String content
        , MessageTypeEnum messageTypeEnum
    ) {
        return ChatMessageDto.builder()
            .chatMessageId(chatMessageId)
            .chatRoomId(chatRoomId)
            .senderId(senderId)
            .content(content)
            .messageTypeEnum(messageTypeEnum)
            .build();
    }

    public void createId(UUID chatMessageId) {
        this.chatMessageId = chatMessageId;
    }
}
