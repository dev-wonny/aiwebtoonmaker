package com.webtoonmaker.api.chat.presentation.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UserUpdateRequest {
    private UUID userId;

    private String email;

    private String password;

    private String userName;

    private String phoneNumber;

    private String role;

    private boolean isBlock = false;

    private String zipCode;

    private String address1;

    private String address2;
}
