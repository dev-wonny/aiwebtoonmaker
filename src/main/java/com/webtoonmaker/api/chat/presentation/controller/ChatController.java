package com.webtoonmaker.api.chat.presentation.controller;

import com.webtoonmaker.api.chat.application.dto.ChatRoomDto;
import com.webtoonmaker.api.chat.application.service.ChatService;
import com.webtoonmaker.api.chat.presentation.request.ChatCreateRequest;
import com.webtoonmaker.api.chat.presentation.request.ChatMessageCreateRequest;
import com.webtoonmaker.api.chat.presentation.response.ChatRoomResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "통합 실시간 채팅 API", description = "채팅방 생성, 목록 조회, 메시지 전송 API")
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    // 채팅방 생성
    @Operation(summary = "채팅방 생성", description = "채팅방 이름을 입력 받아 새로운 채팅방과 참여자 목록을 생성합니다.")
    @PostMapping("/room")
    public ResponseEntity<ChatRoomResponseDto> createRoom(@Valid @RequestBody ChatCreateRequest request) {
        return ResponseEntity.ok(chatService.createChatRoom(request.toDto()));
    }

    // 유저가 속한 채팅방 전체 조회
    @Operation(summary = "유저별 참여한 채팅방 목록 조회", description = "유저별 참여한 모든 채팅방 리스트를 조회합니다.")
    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoomDto>> getParticipantRooms(UUID userId) {
        return ResponseEntity.ok(chatService.getParticipantRooms(userId));
    }

    // 메세지 보내기
    @Operation(summary = "메시지 전송", description = "채팅방에 메시지를 전송합니다.")
    @PostMapping("/send")
    public ResponseEntity<Boolean> sendMessage(@RequestBody ChatMessageCreateRequest req) {
        return ResponseEntity.ok(chatService.sendMessage(req.toDto()));
    }
}