package com.webtoonmaker.api.chat.presentation.response;

import com.webtoonmaker.api.chat.domain.entity.UsersEntity;
import com.webtoonmaker.api.chat.domain.enums.UserRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private UUID userId;
    private String email;
    private String userName;
    private String phoneNumber;
    private UserRoleEnum role;

    private Address address;


    private boolean isBlock;
    private boolean isDeleted;

    @Builder
    private UserResponseDto(
        UUID userId
        , String email
        , String userName
        , String phoneNumber
        , UserRoleEnum role
        , Address address
        , boolean isBlock
        , boolean isDeleted
    ) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.address = address;
        this.isBlock = isBlock;
        this.isDeleted = isDeleted;

    }

    /**
     * 오로지 하나의 객체만 반환하도록 하여 객체를 재사용해 메모리를 아끼도록 유도
     *
     * @param user
     * @return
     */
    public static UserResponseDto fromUser(UsersEntity user) {
        return UserResponseDto.builder()
            .userId(user.getUserId())
            .email(user.getEmail())
            .userName(user.getUserName())
            .phoneNumber(user.getPhoneNumber())
            .role(user.getRole())
            .address(new Address(
                user.getAddressEntity().getZipCode(),
                user.getAddressEntity().getAddress1(),
                user.getAddressEntity().getAddress2()
            ))
            .isBlock(user.isBlock())
            .isDeleted(user.isDeleted())
            .build();
    }
}
