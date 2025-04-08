package com.webtoonmaker.api.chat.presentation.controller;

import com.webtoonmaker.api.chat.application.service.ChatMessageService;
import com.webtoonmaker.api.chat.presentation.request.ChatMessageCreateRequest;
import com.webtoonmaker.api.chat.presentation.response.ChatMessageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "채팅 메시지 API", description = "채팅방 내 메시지 전송 관련 API")

@RestController
@RequestMapping("/chat-room-messages/v1")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @Operation(summary = "채팅 메시지 전송", description = "채팅방에 메시지를 전송합니다.")
    @PostMapping("/{chatRoomId}/messages")
    public ResponseEntity<ChatMessageResponseDto> sendMessage(
        @Parameter(description = "채팅방 UUID", required = true) @PathVariable UUID chatRoomId
        , @Valid @RequestBody ChatMessageCreateRequest req
    ) {
        return ResponseEntity.ok(chatMessageService.create(chatRoomId, req.toDto()));
    }

    //채팅방 메시지 목록 조회
    @Operation(summary = "채팅방 메시지 List 조회", description = "채팅방 메시지 List 조회")
    @GetMapping("/{chatRoomId}/messages")
    public ResponseEntity<List<ChatMessageResponseDto>> getListChatMessage(UUID chatRoomId) {
        return ResponseEntity.ok(chatMessageService.getListChatMessage(chatRoomId));
    }

    //특정 메시지 수정

    //메시지 삭제
}
