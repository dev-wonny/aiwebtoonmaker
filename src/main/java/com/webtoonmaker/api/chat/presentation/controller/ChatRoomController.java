package com.webtoonmaker.api.chat.presentation.controller;

import com.webtoonmaker.api.chat.application.service.ChatRoomService;
import com.webtoonmaker.api.chat.presentation.request.ChatRoomCreateRequest;
import com.webtoonmaker.api.chat.presentation.request.ChatRoomUpdateRequest;
import com.webtoonmaker.api.chat.presentation.response.ChatRoomResponseDto;
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
@RequestMapping("/chat-rooms/v1")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    //생성
    @PostMapping
    public ResponseEntity<ChatRoomResponseDto> createRoom(@Valid @RequestBody ChatRoomCreateRequest request) {
        return ResponseEntity.ok(chatRoomService.createChatRoom(request.toDto()));
    }

    //조회
    @GetMapping("/{chatRoomId}")
    public ResponseEntity<ChatRoomResponseDto> getRoom(@PathVariable UUID chatRoomId) {
        return ResponseEntity.ok(chatRoomService.getChatRoom(chatRoomId));
    }

    //수정
    @PutMapping("/{chatRoomId}")
    public ResponseEntity<ChatRoomResponseDto> updateRoom(@PathVariable UUID chatRoomId, @Valid @RequestBody ChatRoomUpdateRequest request) {
        return ResponseEntity.ok(chatRoomService.updateChatRoom(chatRoomId, request.toDto()));
    }

    //삭제
    @DeleteMapping("/{chatRoomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID chatRoomId) {
        chatRoomService.deleteChatRoom(chatRoomId);
        return ResponseEntity.noContent().build();
    }
}
