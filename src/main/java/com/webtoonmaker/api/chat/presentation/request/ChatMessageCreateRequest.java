package com.webtoonmaker.api.chat.presentation.request;

import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import com.webtoonmaker.api.chat.domain.enums.MessageTypeEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class ChatMessageCreateRequest {
    private UUID chatMessageId;//메시지 고유 ID (PK)
    private UUID chatRoomId;
    private UUID senderId;
    private String content;
    private String messageType;

    public ChatMessageDto toDto() {
        return ChatMessageDto.of(
            this.chatMessageId
            , this.chatRoomId
            , this.senderId
            , this.content
            , MessageTypeEnum.getMessageTypeEnum(this.messageType)
        );
    }
}
