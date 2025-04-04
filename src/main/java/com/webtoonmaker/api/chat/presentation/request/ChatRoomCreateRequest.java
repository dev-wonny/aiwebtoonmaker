package com.webtoonmaker.api.chat.presentation.request;

import com.webtoonmaker.api.chat.application.dto.ChatRoomDto;
import com.webtoonmaker.api.chat.domain.enums.ChatTypeEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class ChatRoomCreateRequest {
    private UUID chatRoomId;
    private String chatRoomName;
    private UUID ownerId;//FK
    private String roomType;

    public ChatRoomDto toDto() {
        return ChatRoomDto.of(
            this.chatRoomId
            , this.chatRoomName
            , this.ownerId
            , ChatTypeEnum.getChatTypeEnum(this.roomType)
        );
    }
}