package com.webtoonmaker.api.chat.application.repository;

import com.webtoonmaker.api.chat.domain.entity.ChatRoomParticipantsEntity;
import com.webtoonmaker.api.chat.domain.enums.ParticipantStatusEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRoomParticipantRepository {
    ChatRoomParticipantsEntity save(ChatRoomParticipantsEntity entity);

    Optional<ChatRoomParticipantsEntity> findByChatRoomParticipantIdAndIsDeletedFalse(UUID chatRoomParticipantId);

    List<ChatRoomParticipantsEntity> findByChatRoom_ChatRoomIdAndIsDeletedFalse(UUID chatRoomId);

    boolean existsByChatRoom_ChatRoomIdAndParticipant_UserIdAndIsDeletedFalse(UUID chatRoomId, UUID userId);

    Optional<ChatRoomParticipantsEntity> findByChatRoom_ChatRoomIdAndParticipant_UserIdAndIsDeletedFalse(UUID chatRoomId, UUID userId);

    Optional<ChatRoomParticipantsEntity> findByChatRoom_ChatRoomIdAndParticipant_UserIdAndStatusAndIsDeletedFalse(UUID chatRoomId, UUID userId, ParticipantStatusEnum participantStatus);

    List<ChatRoomParticipantsEntity> findByParticipant_UserIdAndIsDeletedFalse(UUID userId);

    boolean existsByChatRoom_ChatRoomIdAndParticipant_UserIdAndStatusAndIsDeletedFalse(UUID chatRoomId, UUID userId, ParticipantStatusEnum status);
}
