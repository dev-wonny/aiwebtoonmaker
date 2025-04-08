package com.webtoonmaker.api.chat.presentation.controller;

import com.webtoonmaker.api.chat.application.service.ChatRoomParticipantService;
import com.webtoonmaker.api.chat.presentation.request.ChatRoomParticipantCreateRequest;
import com.webtoonmaker.api.chat.presentation.response.ChatRoomParticipantResponseDto;
import com.webtoonmaker.api.chat.presentation.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "채팅방 참여자 API", description = "채팅방 참가, 퇴장, 조회 등 채팅방 사용자 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-room-participants/v1")
public class ChatRoomParticipantController {
    private final ChatRoomParticipantService participantService;

    // 채팅방 참여
    @Operation(summary = "채팅방 참여", description = "유저가 채팅방에 참여합니다.")
    @PostMapping
    public ResponseEntity<ChatRoomParticipantResponseDto> joinChatRoom(@Valid @RequestBody ChatRoomParticipantCreateRequest req) {
        return ResponseEntity.ok(participantService.join(req.toDto()));
    }

    //채팅방 참여자 전체 조회

    //유저가 속한 모든 채팅방 조회

    //채팅방 퇴장
    @Operation(summary = "채팅방 퇴장", description = "유저가 채팅방에서 퇴장합니다.")
    @DeleteMapping
    public ResponseEntity<CommonResponse> leaveChatRoom(
        @Parameter(description = "채팅방 ID") @RequestParam UUID chatRoomId
        , @Parameter(description = "유저 ID") @RequestParam UUID userId
    ) {
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