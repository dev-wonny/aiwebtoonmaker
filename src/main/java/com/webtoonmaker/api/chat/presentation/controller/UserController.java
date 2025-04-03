package com.webtoonmaker.api.chat.presentation.controller;

import com.webtoonmaker.api.chat.application.dto.UserDto;
import com.webtoonmaker.api.chat.application.service.UserService;
import com.webtoonmaker.api.chat.domain.entity.UsersEntity;
import com.webtoonmaker.api.chat.domain.enums.UserRoleEnum;
import com.webtoonmaker.api.chat.presentation.request.UserCreateRequest;
import com.webtoonmaker.api.chat.presentation.request.UserUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/users/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //생성
    @PostMapping
    public ResponseEntity<UsersEntity> createUser(@Valid @RequestBody UserCreateRequest req) {
        return ResponseEntity.ok(userService.createUser(
                UserDto.of(
                    null
                    , req.getEmail()
                    , req.getPassword()
                    , req.getPassword()
                    , req.getPhoneNumber()
                    , UserRoleEnum.getRoleEnum(req.getRole())
                    , req.isBlock()
                    , req.getZipCode()
                    , req.getAddress1()
                    , req.getAddress2()
                )
            )
        );
    }

    //조회
    @GetMapping("/{userId}")
    public ResponseEntity<UsersEntity> getUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    //수정
    @PutMapping("/{userId}")
    public ResponseEntity<UsersEntity> updateUser(@PathVariable UUID userId,
                                                  @Valid @RequestBody UserUpdateRequest req) {
        return ResponseEntity.ok(userService.updateUser(userId
                , UserDto.of(userId
                    , req.getEmail()
                    , req.getPassword()
                    , req.getPassword()
                    , req.getPhoneNumber()
                    , UserRoleEnum.getRoleEnum(req.getRole())
                    , req.isBlock()
                    , req.getZipCode()
                    , req.getAddress1()
                    , req.getAddress2())
            )
        );
    }

    //삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
