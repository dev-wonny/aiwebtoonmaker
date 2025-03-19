package com.webtoonmaker.api.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    private String roomId;  // 채팅방 ID
    private String sender;  // 보낸 사람
    private String message; // 메시지 내용
}
