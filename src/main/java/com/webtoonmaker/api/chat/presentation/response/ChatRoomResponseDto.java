package com.webtoonmaker.api.chat.presentation.response;

import com.webtoonmaker.api.chat.application.dto.ChatRoomDto;
import com.webtoonmaker.api.chat.domain.entity.ChatRoomsEntity;
import com.webtoonmaker.api.chat.domain.enums.ChatTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ChatRoomResponseDto {
    private UUID chatRoomId;
    private String chatRoomName;
    private UUID ownerId;
    private ChatTypeEnum roomType;

    @Builder
    private ChatRoomResponseDto(UUID chatRoomId, String chatRoomName, UUID ownerId, ChatTypeEnum roomType) {
        this.chatRoomId = chatRoomId;
        this.chatRoomName = chatRoomName;
        this.ownerId = ownerId;
        this.roomType = roomType;
    }

    public static ChatRoomResponseDto from(ChatRoomDto dto) {
        return ChatRoomResponseDto.builder()
            .chatRoomId(dto.getChatRoomId())
            .chatRoomName(dto.getChatRoomName())
            .ownerId(dto.getOwnerId())
            .roomType(dto.getRoomType())
            .build();
    }

    public static ChatRoomResponseDto fromChatRoom(ChatRoomsEntity chatRoom) {
        return ChatRoomResponseDto.builder()
            .chatRoomId(chatRoom.getChatRoomId())
            .chatRoomName(chatRoom.getChatRoomName())
            .ownerId(chatRoom.getOwnerId().getUserId())
            .roomType(chatRoom.getRoomType())
            .build();
    }
}
