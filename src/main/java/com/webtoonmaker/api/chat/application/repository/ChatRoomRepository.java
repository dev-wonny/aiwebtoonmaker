package com.webtoonmaker.api.chat.application.repository;

import com.webtoonmaker.api.chat.domain.entity.ChatRoomsEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRoomRepository {
    Optional<ChatRoomsEntity> save(ChatRoomsEntity entity);

    boolean existsByChatRoomId(UUID chatRoomId);

    List<ChatRoomsEntity> findAll();

    Optional<ChatRoomsEntity> findByChatRoomId(UUID chatRoomId);
}

