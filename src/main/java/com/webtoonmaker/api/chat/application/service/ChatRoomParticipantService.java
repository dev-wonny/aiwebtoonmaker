package com.webtoonmaker.api.chat.application.service;

import com.webtoonmaker.api.chat.application.dto.ChatRoomDto;
import com.webtoonmaker.api.chat.application.dto.ChatRoomParticipantDto;
import com.webtoonmaker.api.chat.application.repository.ChatRoomParticipantRepository;
import com.webtoonmaker.api.chat.domain.entity.ChatRoomParticipantsEntity;
import com.webtoonmaker.api.chat.domain.entity.ChatRoomsEntity;
import com.webtoonmaker.api.chat.domain.entity.UsersEntity;
import com.webtoonmaker.api.chat.domain.enums.ParticipantStatusEnum;
import com.webtoonmaker.api.chat.presentation.response.ChatRoomParticipantResponseDto;
import com.webtoonmaker.api.chat.presentation.response.ChatRoomResponseDto;
import com.webtoonmaker.api.chat.presentation.response.UserResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

        //유저 검증
        UsersEntity userEntity = userService.getUserEntity(dto.getUserId());

        // 참여한 이력 여부 체크
        if (isRoomPaticipant(chatRoomEntity.getChatRoomId(), userEntity.getUserId())) {
            // 기존 데이터 반환
            ChatRoomParticipantsEntity chatRoomParticipantEntity = getChatRoomParticipantEntityByChatRoomIdAndUserId(chatRoomEntity.getChatRoomId(), userEntity.getUserId());

            // 다시 참여 요청 ->활성화 변경
            if (ParticipantStatusEnum.LEFT == chatRoomParticipantEntity.getStatus()) {
                chatRoomParticipantEntity.joinRoom();
            }

            // JOINNED, BANNED 그대로 리턴
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
                , dto.getParticipantStatus()
            )
        );

        return ChatRoomParticipantResponseDto.fromEntity(getChatRoomParticipantEntity(saved.getChatRoomParticipantId()));
    }

    public boolean isActiveParticipantInRoom(UUID chatRoomId, UUID userId, ParticipantStatusEnum status) {
        return participantRepository.existsByChatRoom_ChatRoomIdAndParticipant_UserIdAndStatusAndIsDeletedFalse(chatRoomId, userId, status);
    }

    public boolean isRoomPaticipant(UUID chatRoomId, UUID userId) {
        return participantRepository.existsByChatRoom_ChatRoomIdAndParticipant_UserIdAndIsDeletedFalse(chatRoomId, userId);
    }

    private ChatRoomParticipantsEntity getChatRoomParticipantEntity(UUID chatRoomParticipantId) {
        return participantRepository.findByChatRoomParticipantIdAndIsDeletedFalse(chatRoomParticipantId)
            .orElseThrow(() -> new EntityNotFoundException("chatRoomParticipantId: " + chatRoomParticipantId));

    }

    public List<ChatRoomDto> getListParticipantRooms(UUID userId) {
        return getListChatRoomParticipantEntityByUserId(userId).stream()
            .map(entity -> {
                return ChatRoomDto.of(
                    entity.getChatRoom().getChatRoomId(),
                    entity.getChatRoom().getChatRoomName(),
                    entity.getChatRoom().getOwnerId().getUserId(),
                    entity.getChatRoom().getRoomType()
                );
            })
            .distinct() // 중복 제거
            .toList();
    }

    public List<ChatRoomParticipantsEntity> getListChatRoomParticipantEntityByUserId(UUID userId) {
        return participantRepository.findByParticipant_UserIdAndIsDeletedFalse(userId);
    }


    public ChatRoomParticipantsEntity getChatRoomParticipantEntityByChatRoomIdAndUserId(UUID chatRoomId, UUID userId) {
        return participantRepository.findByChatRoom_ChatRoomIdAndParticipant_UserIdAndIsDeletedFalse(chatRoomId, userId)
            .orElseThrow(() -> new EntityNotFoundException("chatRoomId:" + chatRoomId + ", userId:" + userId));
    }

    public ChatRoomParticipantsEntity getChatRoomParticipantEntityByChatRoomIdAndUserIdAndParticipantStatus(UUID chatRoomId, UUID userId, ParticipantStatusEnum participantStatus) {
        return participantRepository.findByChatRoom_ChatRoomIdAndParticipant_UserIdAndStatusAndIsDeletedFalse(chatRoomId, userId, participantStatus)
            .orElseThrow(() -> new EntityNotFoundException("chatRoomId:" + chatRoomId + ", userId:" + userId + ", participantStatusEnum:" + participantStatus));
    }


    public void leave(UUID chatRoomId, UUID userId) {
        ChatRoomResponseDto chatRoom = chatRoomService.getChatRoom(chatRoomId);
        UserResponseDto user = userService.getUser(userId);

        ChatRoomParticipantsEntity participant = getChatRoomParticipantEntityByChatRoomIdAndUserId(chatRoom.getChatRoomId(), user.getUserId());
        participant.leaveRoom();
    }
}

