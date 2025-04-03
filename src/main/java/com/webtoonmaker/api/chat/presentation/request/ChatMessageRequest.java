package com.webtoonmaker.api.chat.presentation.request;

import com.webtoonmaker.api.chat.domain.enums.MessageTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatMessageRequest {
    private UUID roomId;
    private UUID senderId;
    private String content;
    private MessageTypeEnum messageType;
}
