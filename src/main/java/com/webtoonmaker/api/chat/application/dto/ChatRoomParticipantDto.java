package com.webtoonmaker.api.chat.application.dto;

import com.webtoonmaker.api.chat.domain.enums.ParticipantStatusEnum;
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
    private ParticipantStatusEnum participantStatus;

    @Builder
    private ChatRoomParticipantDto(
        UUID chatRoomParticipantId
        , UUID chatRoomId
        , UUID userId
        , ParticipantStatusEnum participantStatus
    ) {
        this.chatRoomParticipantId = chatRoomParticipantId;
        this.chatRoomId = chatRoomId;
        this.userId = userId;
        this.participantStatus = participantStatus;
    }

    public static ChatRoomParticipantDto of(
        UUID chatRoomParticipantId
        , UUID chatRoomId
        , UUID userId
        , ParticipantStatusEnum participantStatus
    ) {
        return ChatRoomParticipantDto.builder()
            .chatRoomParticipantId(chatRoomParticipantId)
            .chatRoomId(chatRoomId)
            .userId(userId)
            .participantStatus(participantStatus)
            .build();
    }

    public void createId(UUID chatRoomParticipantId) {
        this.chatRoomParticipantId = chatRoomParticipantId;
    }
}
