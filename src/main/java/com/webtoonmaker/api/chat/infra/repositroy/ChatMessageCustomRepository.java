package com.webtoonmaker.api.chat.infra.repositroy;

import com.webtoonmaker.api.chat.domain.entity.ChatMessagesEntity;

import java.util.List;
import java.util.UUID;

public interface ChatMessageCustomRepository {
    public List<ChatMessagesEntity> findRecentMessagesByRoomId(UUID roomId, int limit);
}
