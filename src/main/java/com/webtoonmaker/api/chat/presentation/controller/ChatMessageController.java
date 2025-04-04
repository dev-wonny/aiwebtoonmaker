package com.webtoonmaker.api.chat.presentation.controller;

import com.webtoonmaker.api.chat.application.service.ChatMessageService;
import com.webtoonmaker.api.chat.presentation.request.ChatMessageCreateRequest;
import com.webtoonmaker.api.chat.presentation.response.ChatMessageResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/chat-room-messages/v1")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @PostMapping("/{chatRoomId}/messages")
    public ResponseEntity<ChatMessageResponseDto> sendMessage(@PathVariable UUID chatRoomId, @Valid @RequestBody ChatMessageCreateRequest req) {
        return ResponseEntity.ok(chatMessageService.create(chatRoomId, req.toDto()));
    }
}
