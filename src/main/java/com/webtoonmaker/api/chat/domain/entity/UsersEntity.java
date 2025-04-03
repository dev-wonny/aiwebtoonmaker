package com.webtoonmaker.api.chat.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webtoonmaker.api.chat.domain.enums.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@DynamicUpdate
@Entity
@Table(name = "p_users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
public class UsersEntity extends BaseEntity {
    @Id
    @Column(name = "user_id", columnDefinition = "UUID", nullable = false, unique = true)
    private UUID userId;

    @Email
    @Column(name = "user_email", length = 320, nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "user_password", length = 60, nullable = false)
    private String password;

    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;

    @Column(name = "user_phone_number", length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRoleEnum role;

    @Embedded
    private AddressEntity addressEntity;

    @Column(name = "is_block", nullable = false)
    private boolean isBlock = false;


    @Builder
    private UsersEntity(
        UUID userId
        , String email
        , String password
        , String userName
        , String phoneNumber
        , UserRoleEnum role
        , AddressEntity addressEntity
        , boolean isBlock
    ) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.addressEntity = addressEntity;
        this.isBlock = isBlock;
    }

    public static UsersEntity create(
        UUID userId
        , String email
        , String passwordEncode
        , String userName
        , String phoneNumber
        , UserRoleEnum role
        , String zipCode
        , String address1
        , String address2
    ) {
        return UsersEntity.builder()
            .userId(userId)
            .email(email)
            .password(passwordEncode)
            .userName(userName)
            .phoneNumber(phoneNumber)
            .role(role != null ? role : UserRoleEnum.CUSTOMER)
            .addressEntity(AddressEntity.create(zipCode, address1, address2))
            .build();
    }

    public void updateUserInfo(
        String userName
        , String phoneNumber
        , UserRoleEnum role
    ) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public void updateMyInfo(String userName, String phoneNumber) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void updateUserRole(UserRoleEnum role) {
        this.role = role;
    }

    public void updateAddress(String zipCode, String address1, String address2) {
        this.addressEntity.updateAddress(zipCode, address1, address2);
    }

    public void blockUser() {
        this.isBlock = true;
    }

    public void unblockUser() {
        this.isBlock = false;
    }
}
