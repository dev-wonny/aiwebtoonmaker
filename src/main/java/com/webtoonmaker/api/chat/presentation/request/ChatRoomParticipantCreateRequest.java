package com.webtoonmaker.api.chat.presentation.request;

import com.webtoonmaker.api.chat.application.dto.ChatRoomParticipantDto;
import com.webtoonmaker.api.chat.domain.enums.ParticipantStatusEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class ChatRoomParticipantCreateRequest {
    private UUID chatRoomParticipantId;
    private UUID chatRoomId;
    private UUID userId;
    private String participantStatus;

    public ChatRoomParticipantDto toDto() {
        return ChatRoomParticipantDto.of(
            this.chatRoomParticipantId
            , this.chatRoomId
            , this.userId
            , ParticipantStatusEnum.getParticipantStatusEnum(this.participantStatus)
        );
    }
}
