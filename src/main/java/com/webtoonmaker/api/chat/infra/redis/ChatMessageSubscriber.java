package com.webtoonmaker.api.chat.infra.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class ChatMessageSubscriber implements MessageListener {
    // Redis Pub/Sub을 사용
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Redis에서 받은 메시지: " + new String(message.getBody(), StandardCharsets.UTF_8));
    }
}