package com.webtoonmaker.api.chat.presentation.request;

import com.webtoonmaker.api.chat.application.dto.ChatDto;
import com.webtoonmaker.api.chat.domain.enums.ChatTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ChatCreateRequest {
    private UUID chatRoomId;

    @Schema(description = "채팅방 이름", example = "개발자 모임")
    private String chatRoomName;

    @Schema(description = "채팅방 소유자 UUID", example = "f44d8ad1-9f45-4f0f-8e67-8d67cf4a0f22")
    private UUID ownerId;

    @Schema(description = "채팅방 타입", example = "GROUP")
    private String roomType;

    @Schema(description = "참여자 UUID 리스트", example = "[\"a4c9d7e4-90a2-4c79-b0b1-90f7a236c994\", \"27ae03b2-4aeb-40e5-812a-69b2dd683b6f\"]")
    private List<UUID> participants;

    public ChatDto toDto() {
        return ChatDto.of(
            this.chatRoomId
            , this.chatRoomName
            , this.ownerId
            , ChatTypeEnum.getChatTypeEnum(this.roomType)
            , this.participants
        );
    }
}
