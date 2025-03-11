package com.webtoonmaker.api.chat.repositroy;

import com.webtoonmaker.api.chat.dto.ChatRoom;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ChatRoomRepository {
    //todo: 채팅방을 생성하고, 조회할 수 있도록 "메모리 저장소"를 사용 (실제 서비스에서는 DB 사용)
    private Map<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();

    public List<ChatRoom> findAllRooms() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom createChatRoom(String chatRoomName) {
        String chatRoomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = new ChatRoom(chatRoomId, chatRoomName);
        chatRooms.put(chatRoomId, chatRoom);
        return chatRoom;
    }
}

