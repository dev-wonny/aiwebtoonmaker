package com.webtoonmaker.api.chat.application.dto;

import com.webtoonmaker.api.chat.domain.enums.ChatTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDto {
    private UUID chatRoomId;
    private String chatRoomName;
    private UUID ownerId;//FK
    private ChatTypeEnum roomType;

    @Builder
    private ChatRoomDto(
        UUID chatRoomId
        , String chatRoomName
        , UUID ownerId
        , ChatTypeEnum roomType
    ) {
        this.chatRoomId = chatRoomId;
        this.chatRoomName = chatRoomName;
        this.ownerId = ownerId;
        this.roomType = roomType;
    }

    public static ChatRoomDto of(
        UUID chatRoomId,
        String chatRoomName,
        UUID ownerId,
        ChatTypeEnum roomType
    ) {
        return ChatRoomDto.builder()
            .chatRoomId(chatRoomId)
            .chatRoomName(chatRoomName)
            .ownerId(ownerId)
            .roomType(roomType)
            .build();
    }

    public void createId(UUID chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
}
