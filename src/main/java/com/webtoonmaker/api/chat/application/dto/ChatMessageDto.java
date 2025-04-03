package com.webtoonmaker.api.chat.application.dto;

import com.webtoonmaker.api.chat.domain.enums.MessageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    private UUID roomId;  // 채팅방 ID
    private UUID senderId;  // 보낸 사람
    private String content; // 메시지 내용
    private MessageTypeEnum messageTypeEnum;
}
