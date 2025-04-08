package com.webtoonmaker.api.chat.application.service;

import com.webtoonmaker.api.chat.application.dto.ChatRoomParticipantDto;
import com.webtoonmaker.api.chat.application.repository.ChatRoomParticipantRepository;
import com.webtoonmaker.api.chat.domain.entity.ChatRoomParticipantsEntity;
import com.webtoonmaker.api.chat.domain.entity.ChatRoomsEntity;
import com.webtoonmaker.api.chat.domain.entity.UsersEntity;
import com.webtoonmaker.api.chat.presentation.response.ChatRoomParticipantResponseDto;
import com.webtoonmaker.api.chat.presentation.response.ChatRoomResponseDto;
import com.webtoonmaker.api.chat.presentation.response.UserResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomParticipantService {

    private final ChatRoomParticipantRepository participantRepository;
    private final ChatRoomService chatRoomService;
    private final UserService userService;

    public ChatRoomParticipantResponseDto join(ChatRoomParticipantDto dto) {
        ChatRoomsEntity chatRoomEntity = chatRoomService.getChatRoomEntity(dto.getChatRoomId());
        UsersEntity userEntity = userService.getUserEntity(dto.getUserId());

        // 이미 참여했는지 여부 체크
        if (participantRepository.existsByChatRoomIdAndUserId(chatRoomEntity.getChatRoomId(), userEntity.getUserId())) {
            // 기존 데이터 반환
            ChatRoomParticipantsEntity chatRoomParticipantEntity = getChatRoomParticipantEntityByChatRoomIdAndUserId(chatRoomEntity.getChatRoomId(), userEntity.getUserId());

            // 활성화
            if (!chatRoomParticipantEntity.isActive()) {
                chatRoomParticipantEntity.updateActive(true);
            }

            return ChatRoomParticipantResponseDto.fromEntity(chatRoomParticipantEntity);
        }

        // 서비스 레이어에서 UUID 생성
        final UUID chatRoomParticipantId = dto.getChatRoomParticipantId() != null ? dto.getChatRoomParticipantId() : UUID.randomUUID();
        dto.createId(chatRoomParticipantId);

        ChatRoomParticipantsEntity saved = participantRepository.save(
            ChatRoomParticipantsEntity.create(
                dto.getChatRoomParticipantId()
                , chatRoomEntity
                , userEntity
                , dto.isActive()
            )
        );

        return ChatRoomParticipantResponseDto.fromEntity(getChatRoomParticipantEntity(saved.getChatRoomParticipantId()));
    }

    private ChatRoomParticipantsEntity getChatRoomParticipantEntity(UUID chatRoomParticipantId) {
        return participantRepository.findById(chatRoomParticipantId)
            .orElseThrow(() -> new EntityNotFoundException("chatRoomParticipantId: " + chatRoomParticipantId));

    }

    public ChatRoomParticipantsEntity getChatRoomParticipantEntityByChatRoomIdAndUserId(UUID chatRoomId, UUID userId) {
        return participantRepository.findByChatRoomIdAndUserId(chatRoomId, userId)
            .orElseThrow(() -> new EntityNotFoundException("chatRoomId:" + chatRoomId + ", userId:" + userId));
    }

    public ChatRoomParticipantsEntity getChatRoomParticipantEntityByChatRoomIdAndUserIdAndIsActive(UUID chatRoomId, UUID userId, boolean isActive) {
        return participantRepository.findByChatRoomIdAndUserIdAndIsActive(chatRoomId, userId, isActive)
            .orElseThrow(() -> new EntityNotFoundException("chatRoomId:" + chatRoomId + ", userId:" + userId + ", isActive:" + isActive));
    }


    public void leave(UUID chatRoomId, UUID userId) {
        ChatRoomResponseDto chatRoom = chatRoomService.getChatRoom(chatRoomId);
        UserResponseDto user = userService.getUser(userId);

        ChatRoomParticipantsEntity participant = getChatRoomParticipantEntityByChatRoomIdAndUserIdAndIsActive(chatRoom.getChatRoomId(), user.getUserId(), true);
        participant.updateActive(false);
    }
}

