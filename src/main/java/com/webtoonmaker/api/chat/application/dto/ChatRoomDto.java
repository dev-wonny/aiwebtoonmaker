package com.webtoonmaker.api.chat.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {
    private String chatRoomId;
    private String chatRoomName;
}
