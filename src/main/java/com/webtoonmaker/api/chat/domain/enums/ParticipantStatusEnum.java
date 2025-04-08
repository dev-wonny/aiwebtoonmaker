package com.webtoonmaker.api.chat.domain.enums;

public enum ParticipantStatusEnum {
    JOINED, LEFT, BANNED;

    public static ParticipantStatusEnum getParticipantStatusEnum(String s) {
        try {
            return ParticipantStatusEnum.valueOf(s);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid statusType: " + e);
        }
    }

    public static boolean isJoined(String status) {
        return JOINED.equals(getParticipantStatusEnum(status));
    }

    public static boolean isLeft(String status) {
        return LEFT.equals(getParticipantStatusEnum(status));
    }

    public static boolean isBaned(String status) {
        return BANNED.equals(getParticipantStatusEnum(status));
    }


}
