package com.webtoonmaker.api.chat.application.repository;

import com.webtoonmaker.api.chat.domain.entity.ChatMessagesEntity;

import java.util.List;
import java.util.UUID;

/**
 * 도메인 인터페이스에는 **“무엇을 할 것인지”**만 정의
 */
public interface ChatMessageRepository {
    List<ChatMessagesEntity> findRecentMessagesByRoomId(UUID roomId, int limit);
}