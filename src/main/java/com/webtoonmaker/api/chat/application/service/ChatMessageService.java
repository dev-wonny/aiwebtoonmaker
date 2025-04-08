package com.webtoonmaker.api.chat.application.service;

import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import com.webtoonmaker.api.chat.application.repository.ChatMessageRepository;
import com.webtoonmaker.api.chat.domain.entity.ChatMessagesEntity;
import com.webtoonmaker.api.chat.presentation.response.ChatMessageResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessageResponseDto create(UUID chatRoomId, ChatMessageDto dto) {
        // 서비스 레이어에서 UUID 생성
        final UUID chatMessageId = dto.getChatMessageId() != null ? dto.getChatMessageId() : UUID.randomUUID();
        dto.createId(chatMessageId);

        ChatMessagesEntity chatMessage = ChatMessagesEntity.create(
            dto.getChatMessageId()
            , chatRoomId
            , dto.getSenderId()
            , dto.getContent()
            , dto.getMessageTypeEnum()
        );

        // DB 저장
        ChatMessagesEntity save = chatMessageRepository.save(chatMessage);

        return getChatMessage(save.getChatMessageId());
    }

    public ChatMessagesEntity getChatMessagesEntity(UUID chatMessageId) {
        return chatMessageRepository.findById(chatMessageId)
            .orElseThrow(() -> new EntityNotFoundException("ChatMessage not found: " + chatMessageId));
    }

    public ChatMessageResponseDto getChatMessage(UUID chatMessageId) {
        return ChatMessageResponseDto.fromEntity(getChatMessagesEntity(chatMessageId));
    }
}
