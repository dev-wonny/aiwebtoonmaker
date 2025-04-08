package com.webtoonmaker.api.chat.presentation.controller;

import com.webtoonmaker.api.chat.application.service.UserService;
import com.webtoonmaker.api.chat.presentation.request.UserCreateRequest;
import com.webtoonmaker.api.chat.presentation.request.UserUpdateRequest;
import com.webtoonmaker.api.chat.presentation.response.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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


@Tag(name = "유저 API", description = "유저 생성, 조회, 수정, 삭제 API")
@RestController
@RequestMapping("/users/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //생성
    @Operation(summary = "유저 생성", description = "유저를 신규로 생성합니다.")
    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateRequest req) {
        UserResponseDto user = userService.createUser(req.toDto());
        return new ResponseEntity<>(user.getEmail(), HttpStatus.OK);
    }

    //조회
    @Operation(summary = "유저 조회", description = "userId를 통해 유저 정보를 조회합니다.")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@Parameter(description = "유저 UUID", required = true) @PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    //수정
    @Operation(summary = "유저 정보 수정", description = "유저 정보를 수정합니다.")
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@Parameter(description = "유저 UUID", required = true) @PathVariable UUID userId, @Valid @RequestBody UserUpdateRequest req) {
        return ResponseEntity.ok(userService.updateUser(userId, req.toDto()));
    }

    //삭제
    @Operation(summary = "유저 삭제", description = "유저 정보를 삭제합니다.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "유저 UUID", required = true) @PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
