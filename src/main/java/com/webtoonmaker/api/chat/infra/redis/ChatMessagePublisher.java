package com.webtoonmaker.api.chat.infra.redis;

import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatMessagePublisher {
    private final RedisTemplate<String, Object> redisTemplate;

    public ChatMessagePublisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Redis를 통해 채팅 메시지를 다른 서버에도 전달
    public void publish(String channel, ChatMessageDto message) {
        redisTemplate.convertAndSend(channel, message);
    }
}

