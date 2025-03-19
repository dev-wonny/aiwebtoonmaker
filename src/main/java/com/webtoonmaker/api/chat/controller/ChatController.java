package com.webtoonmaker.api.chat.controller;

import com.webtoonmaker.api.chat.dto.ChatRoomDto;
import com.webtoonmaker.api.chat.repositroy.ChatRoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatRoomRepository chatRoomRepository;

    public ChatController(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    // 생성
    @PostMapping("/room")
    public ResponseEntity<ChatRoomDto> createRoom(@RequestParam String chatRoomName) {
        return ResponseEntity.ok(chatRoomRepository.createChatRoom(chatRoomName));
    }

    // 전체 조회
    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoomDto>> getRooms() {
        return ResponseEntity.ok(chatRoomRepository.findAllRooms());
    }
}
