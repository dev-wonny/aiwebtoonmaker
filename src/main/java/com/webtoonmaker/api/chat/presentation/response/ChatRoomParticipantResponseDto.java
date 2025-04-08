package com.webtoonmaker.api.chat.presentation.response;

import com.webtoonmaker.api.chat.application.dto.ChatRoomParticipantDto;
import com.webtoonmaker.api.chat.domain.entity.ChatRoomParticipantsEntity;
import com.webtoonmaker.api.chat.domain.enums.ParticipantStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ChatRoomParticipantResponseDto {
    private UUID chatRoomParticipantId;
    private UUID chatRoomId;
    private UUID userId;
    private ParticipantStatusEnum participantStatus;

    @Builder
    private ChatRoomParticipantResponseDto(
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

    public static ChatRoomParticipantResponseDto from(ChatRoomParticipantDto dto) {
        return ChatRoomParticipantResponseDto.builder()
            .chatRoomParticipantId(dto.getChatRoomParticipantId())
            .chatRoomId(dto.getChatRoomId())
            .userId(dto.getUserId())
            .participantStatus(dto.getParticipantStatus())
            .build();
    }

    public static ChatRoomParticipantResponseDto fromEntity(ChatRoomParticipantsEntity entity) {
        return ChatRoomParticipantResponseDto.builder()
            .chatRoomParticipantId(entity.getChatRoomParticipantId())
            .chatRoomId(entity.getChatRoom().getChatRoomId())
            .userId(entity.getParticipant().getUserId())
            .participantStatus(entity.getStatus())
            .build();
    }
}
