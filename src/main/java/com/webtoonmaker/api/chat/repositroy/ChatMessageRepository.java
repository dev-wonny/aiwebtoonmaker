package com.webtoonmaker.api.chat.repositroy;

import com.webtoonmaker.api.chat.dto.ChatMessageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageDto, Long> {
    List<ChatMessageDto> findByRoomIdOrderByTimestampAsc(String roomId);
}