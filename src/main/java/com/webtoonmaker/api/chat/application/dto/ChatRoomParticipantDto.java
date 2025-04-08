package com.webtoonmaker.api.chat.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomParticipantDto {
    private UUID chatRoomParticipantId;
    private UUID chatRoomId;
    private UUID userId;
    private boolean isActive;

    @Builder
    private ChatRoomParticipantDto(
        UUID chatRoomParticipantId
        , UUID chatRoomId
        , UUID userId
        , boolean isActive
    ) {
        this.chatRoomParticipantId = chatRoomParticipantId;
        this.chatRoomId = chatRoomId;
        this.userId = userId;
        this.isActive = isActive;
    }

    public static ChatRoomParticipantDto of(
        UUID chatRoomParticipantId
        , UUID chatRoomId
        , UUID userId
        , boolean isActive
    ) {
        return ChatRoomParticipantDto.builder()
            .chatRoomParticipantId(chatRoomParticipantId)
            .chatRoomId(chatRoomId)
            .userId(userId)
            .isActive(isActive)
            .build();
    }

    public void createId(UUID chatRoomParticipantId) {
        this.chatRoomParticipantId = chatRoomParticipantId;
    }
}
