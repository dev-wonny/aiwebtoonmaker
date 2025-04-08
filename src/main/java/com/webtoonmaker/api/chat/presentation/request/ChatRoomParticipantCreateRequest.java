package com.webtoonmaker.api.chat.presentation.request;

import com.webtoonmaker.api.chat.application.dto.ChatRoomParticipantDto;
import lombok.Data;

import java.util.UUID;

@Data
public class ChatRoomParticipantCreateRequest {
    private UUID chatRoomParticipantId;
    private UUID chatRoomId;
    private UUID userId;
    private boolean isActive;

    public ChatRoomParticipantDto toDto() {
        return ChatRoomParticipantDto.of(
            this.chatRoomParticipantId
            , this.chatRoomId
            , this.userId
            , this.isActive
        );
    }
}
