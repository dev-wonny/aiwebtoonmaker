package com.webtoonmaker.api.chat.application.repository;

import com.webtoonmaker.api.chat.domain.entity.ChatRoomParticipantsEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRoomParticipantRepository {
    ChatRoomParticipantsEntity save(ChatRoomParticipantsEntity entity);

    Optional<ChatRoomParticipantsEntity> findByIdAndIsDeletedFalse(UUID chatRoomParticipantId);

    List<ChatRoomParticipantsEntity> findByChatRoomIdAndIsDeletedFalse(UUID chatRoomId);

    boolean existsByChatRoomIdAndUserIdAndIsDeletedFalse(UUID chatRoomId, UUID userId);

//    Optional<ChatRoomParticipantsEntity> findByChatRoomIdAndUserIdAndIsDeletedFalse(UUID chatRoomId, UUID userId);
//    Optional<ChatRoomParticipantsEntity> findByChatRoomIdAndUserIdAndIsDeletedFalse(UUID chatRoomId, UUID userId, boolean isActive);

    Optional<ChatRoomParticipantsEntity> findByChatRoom_ChatRoomIdAndParticipant_UserIdAndIsDeletedFalse(UUID chatRoomId, UUID userId);

    Optional<ChatRoomParticipantsEntity> findByChatRoom_ChatRoomIdAndParticipant_UserIdAndIsActiveAndIsDeletedFalse(UUID chatRoomId, UUID userId, boolean isActive);


    List<ChatRoomParticipantsEntity> findAll();
    List<ChatRoomParticipantsEntity> findByParticipant_UserIdAndIsDeletedFalse(UUID userId);


    void deleteById(UUID id);

    boolean existsByChatRoomIdAndParticipantIdAndIsActiveTrueAndIsDeletedFalse(UUID chatRoomId, UUID userId);
}
