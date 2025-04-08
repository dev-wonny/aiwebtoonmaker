package com.webtoonmaker.api.chat.presentation.controller;

import com.webtoonmaker.api.chat.application.service.ChatRoomParticipantService;
import com.webtoonmaker.api.chat.presentation.request.ChatRoomParticipantCreateRequest;
import com.webtoonmaker.api.chat.presentation.response.ChatRoomParticipantResponseDto;
import com.webtoonmaker.api.chat.presentation.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-room-participants/v1")
public class ChatRoomParticipantController {
    private final ChatRoomParticipantService participantService;

    // 채팅방 참여
    @PostMapping
    public ResponseEntity<ChatRoomParticipantResponseDto> joinChatRoom(@Valid @RequestBody ChatRoomParticipantCreateRequest req) {
        return ResponseEntity.ok(participantService.join(req.toDto()));
    }

    //채팅방 참여자 전체 조회

    //유저가 속한 모든 채팅방 조회

    //채팅방 퇴장
    @DeleteMapping
    public ResponseEntity<CommonResponse> leaveChatRoom(@RequestParam UUID chatRoomId, @RequestParam UUID userId) {
        participantService.leave(chatRoomId, userId);
        return ResponseEntity.ok(
            (CommonResponse) CommonResponse.ok(
                "채팅방에서 퇴장했습니다.",
                Map.of(
                    "chatRoomId", chatRoomId,
                    "userId", userId
                )
            )
        );
    }
}