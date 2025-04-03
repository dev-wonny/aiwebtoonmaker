package com.webtoonmaker.api.chat.application.service;

import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import com.webtoonmaker.api.chat.application.dto.ChatRoomDto;
import com.webtoonmaker.api.chat.application.dto.MyChatRoomDto;
import com.webtoonmaker.api.chat.application.repository.ChatRoomRepository;
import com.webtoonmaker.api.chat.domain.entity.ChatMessagesEntity;
import com.webtoonmaker.api.chat.domain.entity.UsersEntity;
import com.webtoonmaker.api.chat.domain.enums.MessageTypeEnum;
import com.webtoonmaker.api.chat.infra.repositroy.ChatMessageJpaRepository;
import com.webtoonmaker.api.chat.infra.repositroy.ChatMessageRepositoryImpl;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChatService {

    private final KafkaTemplate<String, ChatMessageDto> kafkaTemplate;
    private final ChatMessageRepositoryImpl chatMessageRepositoryImpl;
    private final ChatMessageJpaRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;

    public ChatService(KafkaTemplate<String, ChatMessageDto> kafkaTemplate, ChatMessageRepositoryImpl chatMessageRepositoryImpl, ChatMessageJpaRepository chatMessageRepository,
                       ChatRoomRepository chatRoomRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.chatMessageRepositoryImpl = chatMessageRepositoryImpl;
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    @Transactional
    public void sendMessage(UUID roomId, UUID senderId, String content, MessageTypeEnum messageType) {
        // 1. 메시지 저장


        // 2. Kafka에 메시지 발행
        ChatMessageDto messageDto = new ChatMessageDto(roomId, senderId, content, messageType);
        kafkaTemplate.send("chat-messages", roomId.toString(), messageDto);
    }

    public void saveMessage(ChatMessageDto dto) {
        ChatMessagesEntity chatMessagesEntity = ChatMessagesEntity.create(
            UUID.randomUUID()
            , dto.getRoomId()
            , dto.getSenderId()
            , dto.getContent()
            , dto.getMessageTypeEnum()
        );
        chatMessageRepository.save(chatMessagesEntity);

    }

    public void createGroupRoom(String chatRoomName) {

    }

    public List<ChatRoomDto> getGroupchatRooms() {
        return null;
    }

    public void addParticipantToGroupChat(Long roomId) {

    }


    public void addParticipantToRoom(ChatRoomDto chatRoomDto, UsersEntity user) {

    }

    public List<ChatMessageDto> getChatHistory(Long roomId) {
        return null;
    }

    public boolean isRoomPaticipant(String email, Long roomId) {
        return false;
    }

    public void messageRead(Long roomId) {

    }

    public List<MyChatRoomDto> getMyChatRooms() {
        return null;
    }

    public void leaveGroupChatRoom(Long roomId) {

    }

    public Long getOrCreatePrivateRoom(Long otherMemberId) {
        return 0L;
    }
}
