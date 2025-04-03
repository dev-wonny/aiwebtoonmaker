package com.webtoonmaker.api.chat.application.repository;

import com.webtoonmaker.api.chat.application.dto.ChatRoomDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ChatRoomRepository {
    //todo: 채팅방을 생성하고, 조회할 수 있도록 "메모리 저장소"를 사용 (실제 서비스에서는 DB 사용)
    private Map<String, ChatRoomDto> chatRooms = new ConcurrentHashMap<>();

    public List<ChatRoomDto> findAllRooms() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomDto findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoomDto createChatRoom(String chatRoomName) {
        String chatRoomId = UUID.randomUUID().toString();
        ChatRoomDto chatRoomDto = new ChatRoomDto(chatRoomId, chatRoomName);
        chatRooms.put(chatRoomId, chatRoomDto);
        return chatRoomDto;
    }
}

