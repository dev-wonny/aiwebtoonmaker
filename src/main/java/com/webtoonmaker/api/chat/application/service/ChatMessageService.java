package com.webtoonmaker.api.chat.application.service;

import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import com.webtoonmaker.api.chat.application.repository.ChatMessageRepository;
import com.webtoonmaker.api.chat.domain.entity.ChatMessagesEntity;
import com.webtoonmaker.api.chat.presentation.response.ChatMessageResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessageResponseDto create(UUID chatRoomId, ChatMessageDto dto) {
        ChatMessagesEntity chatMessage = ChatMessagesEntity.create(
            chatRoomId
            , dto.getSenderId()
            , dto.getContent()
            , dto.getMessageTypeEnum()
        );

        // DB 저장
        ChatMessagesEntity saved = chatMessageRepository.save(chatMessage);

        return getChatMessage(saved.getChatMessageId());
    }

    public ChatMessagesEntity getChatMessagesEntity(UUID chatMessageId) {
        return chatMessageRepository.findById(chatMessageId)
            .orElseThrow(() -> new EntityNotFoundException("ChatMessage not found: " + chatMessageId));
    }

    public ChatMessageResponseDto getChatMessage(UUID chatMessageId) {
        return ChatMessageResponseDto.fromEntity(getChatMessagesEntity(chatMessageId));
    }

    public List<ChatMessageResponseDto> getListChatMessage(UUID chatRoomId) {
        List<ChatMessagesEntity> list = chatMessageRepository.findByChatRoomIdOrderByCreatedAtDesc(chatRoomId);
        return list.stream()
            .map(entity -> ChatMessageResponseDto.fromEntity(entity))
            .distinct() // 중복 제거
            .toList();
    }
}
