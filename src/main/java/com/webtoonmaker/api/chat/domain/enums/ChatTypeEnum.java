package com.webtoonmaker.api.chat.domain.enums;

import lombok.Getter;

@Getter
public enum ChatTypeEnum {
    ONE_TO_ONE, GROUP;

    public static ChatTypeEnum getChatTypeEnum(String s) {
        try {
            return ChatTypeEnum.valueOf(s);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid chatType: " + e);
        }

    }
}
