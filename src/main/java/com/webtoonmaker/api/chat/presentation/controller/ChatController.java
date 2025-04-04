package com.webtoonmaker.api.chat.presentation.controller;

import com.webtoonmaker.api.chat.application.dto.ChatRoomDto;
import com.webtoonmaker.api.chat.application.repository.ChatRoomRepository;
import com.webtoonmaker.api.chat.application.service.ChatService;
import com.webtoonmaker.api.chat.presentation.request.ChatMessageCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;

    public ChatController(ChatRoomRepository chatRoomRepository, ChatService chatService) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatService = chatService;
    }

    // 채팅방 생성
    @PostMapping("/room")
    public ResponseEntity<ChatRoomDto> createRoom(@RequestParam String chatRoomName) {
        return ResponseEntity.ok(null);
    }

    // 채팅방 전체 조회
    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoomDto>> getRooms() {
        return ResponseEntity.ok(null);
    }

    // 메세지 보내기
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody ChatMessageCreateRequest req) {
        chatService.sendMessage(req.toDto());
        return ResponseEntity.ok().build();
    }
}