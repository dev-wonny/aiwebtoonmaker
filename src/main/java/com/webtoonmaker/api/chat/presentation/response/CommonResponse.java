package com.webtoonmaker.api.chat.presentation.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class CommonResponse {
    private String message;

    @Builder
    private CommonResponse(String message) {
        this.message = message;
    }

    public static CommonResponse ok(String msg) {
        return CommonResponse.builder()
            .message(msg)
            .build();
    }

    public static <K, V> Object ok(String s, Map<K, V> map) {
        return CommonResponse.builder()
            .message(s + map.toString())
            .build();
    }
}
