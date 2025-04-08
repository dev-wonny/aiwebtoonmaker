package com.webtoonmaker.api.chat.presentation.controller;

import com.webtoonmaker.api.chat.application.service.ChatRoomService;
import com.webtoonmaker.api.chat.presentation.request.ChatRoomCreateRequest;
import com.webtoonmaker.api.chat.presentation.request.ChatRoomUpdateRequest;
import com.webtoonmaker.api.chat.presentation.response.ChatRoomResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "채팅방 API", description = "채팅방 생성, 조회, 수정, 삭제 기능 제공")
@RestController
@RequestMapping("/chat-rooms/v1")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    //생성
    @Operation(summary = "채팅방 생성", description = "새로운 채팅방을 생성합니다.")
    @PostMapping
    public ResponseEntity<ChatRoomResponseDto> createRoom(@Valid @RequestBody ChatRoomCreateRequest request) {
        return ResponseEntity.ok(chatRoomService.createChatRoom(request.toDto()));
    }

    //조회
    @Operation(summary = "채팅방 조회", description = "채팅방 ID를 통해 채팅방 정보를 조회합니다.")
    @GetMapping("/{chatRoomId}")
    public ResponseEntity<ChatRoomResponseDto> getRoom(@Parameter(description = "채팅방 UUID") @PathVariable UUID chatRoomId) {
        return ResponseEntity.ok(chatRoomService.getChatRoom(chatRoomId));
    }

    //수정
    @Operation(summary = "채팅방 수정", description = "채팅방 ID를 기준으로 정보를 수정합니다.")
    @PutMapping("/{chatRoomId}")
    public ResponseEntity<ChatRoomResponseDto> updateRoom(
        @Parameter(description = "채팅방 UUID") @PathVariable UUID chatRoomId
        , @Valid @RequestBody ChatRoomUpdateRequest request
    ) {
        return ResponseEntity.ok(chatRoomService.updateChatRoom(chatRoomId, request.toDto()));
    }

    //삭제
    @Operation(summary = "채팅방 삭제", description = "채팅방 ID를 기준으로 채팅방을 삭제합니다.")
    @DeleteMapping("/{chatRoomId}")
    public ResponseEntity<Void> deleteRoom(@Parameter(description = "채팅방 UUID") @PathVariable UUID chatRoomId) {
        chatRoomService.deleteChatRoom(chatRoomId);
        return ResponseEntity.noContent().build();
    }
}
