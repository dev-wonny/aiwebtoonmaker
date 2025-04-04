package com.webtoonmaker.api.chat.application.service;

import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import com.webtoonmaker.api.chat.application.repository.ChatMessageRepository;
import com.webtoonmaker.api.chat.domain.entity.ChatMessagesEntity;
import com.webtoonmaker.api.chat.presentation.response.ChatMessageResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {// 여전히 컴파일 오류 @Qualifier("") 넣으라고함
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessageResponseDto create(UUID chatRoomId, ChatMessageDto dto) {
        // 서비스 레이어에서 UUID 생성
        final UUID chatMessageId = dto.getChatMessageId() != null ? dto.getChatMessageId() : UUID.randomUUID();
        dto.createId(chatMessageId);

        ChatMessagesEntity chatMessage = ChatMessagesEntity.create(
            chatMessageId
            , chatRoomId
            , dto.getSenderId()
            , dto.getContent()
            , dto.getMessageTypeEnum()
        );

        // DB 저장
        chatMessageRepository.save(chatMessage);//여기서 ChatMessageRepository 에 save 추가하라고 하고

        // 카프카 보내기 추가
        return ChatMessageResponseDto.fromMessage(chatMessage);
    }
}
