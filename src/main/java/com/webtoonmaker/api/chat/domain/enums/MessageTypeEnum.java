package com.webtoonmaker.api.chat.domain.enums;

import lombok.Getter;

@Getter
public enum MessageTypeEnum {
    TEXT, IMAGE, VIDEO;

    public static MessageTypeEnum getMessageTypeEnum(String s) {
        try {
            return MessageTypeEnum.valueOf(s);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid messageType: " + e);
        }

    }
}
