package com.webtoonmaker.api.chat.application.repository;

import com.webtoonmaker.api.chat.domain.entity.ChatRoomParticipantsEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRoomParticipantRepository {
    ChatRoomParticipantsEntity save(ChatRoomParticipantsEntity entity);

    Optional<ChatRoomParticipantsEntity> findById(UUID chatRoomParticipantId);

    List<ChatRoomParticipantsEntity> findByChatRoomId(UUID chatRoomId);

    boolean existsByChatRoomIdAndUserId(UUID chatRoomId, UUID userId);

    Optional<ChatRoomParticipantsEntity> findByChatRoomIdAndUserId(UUID chatRoomId, UUID userId);

    Optional<ChatRoomParticipantsEntity> findByChatRoomIdAndUserIdAndIsActive(UUID chatRoomId, UUID userId, boolean isActive);

    List<ChatRoomParticipantsEntity> findAll();

    void deleteById(UUID id);

}
