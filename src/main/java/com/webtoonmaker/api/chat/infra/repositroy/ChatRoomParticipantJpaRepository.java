package com.webtoonmaker.api.chat.infra.repositroy;

import com.webtoonmaker.api.chat.application.repository.ChatRoomParticipantRepository;
import com.webtoonmaker.api.chat.domain.entity.ChatRoomParticipantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRoomParticipantJpaRepository extends JpaRepository<ChatRoomParticipantsEntity, UUID>, ChatRoomParticipantRepository {
    //    Optional<ChatRoomParticipantsEntity> findByChatRoomIdAndUserIdAndIsActive(UUID chatRoomId, UUID userId, boolean isActive);
//    Optional<ChatRoomParticipantsEntity> findByChatRoom_ChatRoomIdAndParticipantId_UserIdAndIsActive(UUID chatRoomId, UUID userId, boolean isActive);
    Optional<ChatRoomParticipantsEntity> findByIdAndIsDeletedFalse(UUID chatRoomParticipantId);

    List<ChatRoomParticipantsEntity> findByChatRoom_ChatRoomIdAndIsDeletedFalse(UUID chatRoomId);

    boolean existsByChatRoom_ChatRoomIdAndParticipant_UserIdAndIsDeletedFalse(UUID chatRoomId, UUID userId);

    Optional<ChatRoomParticipantsEntity> findByChatRoom_ChatRoomIdAndParticipant_UserIdAndIsDeletedFalse(UUID chatRoomId, UUID userId);

    Optional<ChatRoomParticipantsEntity> findByChatRoom_ChatRoomIdAndParticipant_UserIdAndIsActiveAndIsDeletedFalse(UUID chatRoomId, UUID userId, boolean isActive);

    List<ChatRoomParticipantsEntity> findByParticipant_UserIdAndIsDeletedFalse(UUID userId);

}
