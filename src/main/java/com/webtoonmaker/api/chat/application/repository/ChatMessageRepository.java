package com.webtoonmaker.api.chat.application.repository;

import com.webtoonmaker.api.chat.domain.entity.ChatMessagesEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 도메인 인터페이스에는 **“무엇을 할 것인지”**만 정의
 * Domain Interface (Application Layer)
 */
public interface ChatMessageRepository {
    List<ChatMessagesEntity> findByChatRoomIdOrderByCreatedAtDesc(UUID chatRoomId);

    Optional<ChatMessagesEntity> findById(UUID chatMessageId); // 이거 추가해줘야 함

    ChatMessagesEntity save(ChatMessagesEntity chatMessage);// 추가하면 ChatMessageRepositoryImpl에서 save를 구현해야함
}