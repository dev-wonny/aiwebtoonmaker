package com.webtoonmaker.api.chat.infra.kafka;

import com.webtoonmaker.api.chat.infra.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonConverter jsonConverter;

    public void sendMessage(String topic, String key, String message) {
        kafkaTemplate.send(topic, key, message);
    }
}