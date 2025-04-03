package com.webtoonmaker.api.chat.presentation.request;

import com.webtoonmaker.api.chat.application.dto.UserDto;
import com.webtoonmaker.api.chat.domain.enums.UserRoleEnum;
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

    public UserDto toDto() {
        return UserDto.of(
            this.userId,
            this.email,
            this.password,
            this.userName,
            this.phoneNumber,
            UserRoleEnum.getRoleEnum(this.role),
            this.isBlock,
            this.zipCode,
            this.address1,
            this.address2
        );
    }
}
