package com.webtoonmaker.api.chat.presentation.controller;

import com.webtoonmaker.api.chat.application.service.UserService;
import com.webtoonmaker.api.chat.presentation.request.UserCreateRequest;
import com.webtoonmaker.api.chat.presentation.request.UserUpdateRequest;
import com.webtoonmaker.api.chat.presentation.response.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateRequest req) {
        UserResponseDto user = userService.createUser(req.toDto());
        return new ResponseEntity<>(user.getEmail(), HttpStatus.OK);
    }

    //조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    //수정
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID userId, @Valid @RequestBody UserUpdateRequest req) {
        return ResponseEntity.ok(userService.updateUser(userId, req.toDto()));
    }

    //삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
