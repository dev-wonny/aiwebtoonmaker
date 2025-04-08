package com.webtoonmaker.api.chat.application.service;

import com.webtoonmaker.api.chat.application.dto.ChatRoomDto;
import com.webtoonmaker.api.chat.application.repository.ChatRoomRepository;
import com.webtoonmaker.api.chat.domain.entity.ChatRoomsEntity;
import com.webtoonmaker.api.chat.domain.entity.UsersEntity;
import com.webtoonmaker.api.chat.presentation.response.ChatRoomResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;

    public ChatRoomService(ChatRoomRepository chatRoomRepository, UserService userService) {
        this.chatRoomRepository = chatRoomRepository;
        this.userService = userService;
    }

    public ChatRoomResponseDto createChatRoom(ChatRoomDto dto) {
        //사용자 검증
        UsersEntity userEntity = userService.getUserEntity(dto.getOwnerId());

        // 서비스 레이어에서 UUID 생성
        final UUID chatRoomId = dto.getChatRoomId() != null ? dto.getChatRoomId() : UUID.randomUUID();
        dto.createId(chatRoomId);

        ChatRoomsEntity chatRoom = ChatRoomsEntity.create(
            dto.getChatRoomId()
            , dto.getChatRoomName()
            , userEntity
            , dto.getRoomType()
        );

        ChatRoomsEntity saved = chatRoomRepository.save(chatRoom);

        return ChatRoomResponseDto.fromChatRoom(saved);
    }

    //    @Transactional(readOnly = true)
    public ChatRoomResponseDto getChatRoom(UUID chatRoomId) {
        ChatRoomsEntity chatRoom = getChatRoomEntity(chatRoomId);
        return ChatRoomResponseDto.fromChatRoom(chatRoom);
    }

    public boolean isValidByChatRoomId(UUID chatRoomId) {
        return chatRoomRepository.existsByChatRoomIdAndIsDeletedFalse(chatRoomId);
    }

    public ChatRoomsEntity getChatRoomEntity(UUID chatRoomId) {
        return chatRoomRepository.findByChatRoomId(chatRoomId)
            .orElseThrow(() -> new EntityNotFoundException("Chat room not found"));
    }

    public ChatRoomResponseDto updateChatRoom(UUID chatRoomId, ChatRoomDto dto) {
        ChatRoomsEntity chatRoom = getChatRoomEntity(chatRoomId);

        chatRoom.updateChatRoomInfo(dto.getChatRoomId()
            , dto.getChatRoomName()
            , chatRoom.getOwnerId()
            , dto.getRoomType());
        return ChatRoomResponseDto.fromChatRoom(chatRoom);
    }

    public void deleteChatRoom(UUID chatRoomId) {
        ChatRoomsEntity chatRoom = getChatRoomEntity(chatRoomId);
        chatRoom.setDeleted(true);
    }
}
