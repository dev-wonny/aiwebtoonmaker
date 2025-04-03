package com.webtoonmaker.api.chat.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyChatRoomDto {
    private Long roomId;
    private String roomName;
    private String isGroupChat;
    private Long unReadCount;
}
