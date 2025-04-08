package com.webtoonmaker.api.chat.application.dto;

import com.webtoonmaker.api.chat.domain.enums.ChatTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatDto {
    private UUID chatRoomId;
    private String chatRoomName;
    private UUID ownerId;//FK
    private ChatTypeEnum roomType;
    private List<UUID> userList;

    @Builder
    private ChatDto(
        UUID chatRoomId
        , String chatRoomName
        , UUID ownerId
        , ChatTypeEnum roomType
        , List<UUID> userList
    ) {
        this.chatRoomId = chatRoomId;
        this.chatRoomName = chatRoomName;
        this.ownerId = ownerId;
        this.roomType = roomType;
        this.userList = userList;
    }

    public static ChatDto of(
        UUID chatRoomId
        , String chatRoomName
        , UUID ownerId
        , ChatTypeEnum roomType
        , List<UUID> userList
    ) {
        return ChatDto.builder()
            .chatRoomId(chatRoomId)
            .chatRoomName(chatRoomName)
            .ownerId(ownerId)
            .roomType(roomType)
            .userList(userList)
            .build();
    }

    public void createId(UUID chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public ChatRoomDto toChatRoomDto() {
        return ChatRoomDto.of(
            this.chatRoomId
            , this.chatRoomName
            , this.ownerId
            , this.roomType
        );
    }
}
