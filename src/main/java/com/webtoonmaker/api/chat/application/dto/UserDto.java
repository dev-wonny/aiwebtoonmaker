package com.webtoonmaker.api.chat.application.dto;

import com.webtoonmaker.api.chat.domain.enums.UserRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private UUID userId;
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private UserRoleEnum role;
    private boolean isBlock;

    private String zipCode;
    private String address1;
    private String address2;

    @Builder
    private UserDto(
        UUID userId
        , String email
        , String password
        , String userName
        , String phoneNumber
        , UserRoleEnum role
        , boolean isBlock
        , String zipCode
        , String address1
        , String address2
    ) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.isBlock = isBlock;
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
    }

    public static UserDto of(
        UUID userId
        , String email
        , String password
        , String userName
        , String phoneNumber
        , UserRoleEnum role
        , boolean isBlock
        , String zipCode
        , String address1
        , String address2
    ) {
        return UserDto.builder()
            .userId(userId)
            .email(email)
            .password(password)
            .userName(userName)
            .phoneNumber(phoneNumber)
            .role(role)
            .isBlock(isBlock)
            .zipCode(zipCode)
            .address1(address1)
            .address2(address2)
            .build();
    }

    public void createId(UUID userId) {
        this.userId = userId;
    }
}
