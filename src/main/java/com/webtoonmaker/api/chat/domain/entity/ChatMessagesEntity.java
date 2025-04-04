package com.webtoonmaker.api.chat.domain.entity;

import com.webtoonmaker.api.chat.domain.enums.MessageTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Table(name = "p_chat_messages")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class ChatMessagesEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "chat_message_id", updatable = false, nullable = false)
    private UUID chatMessageId;//메시지 고유 ID (PK)

    @Column(name = "chat_room_id", nullable = false)
    private UUID chatRoomId;//채팅방 ID (FK), 객체지향으로 할까 말까?

    @Column(name = "sender_id", nullable = false)
    private UUID senderId;  // 메시지 보낸 사용자

    @Column(name = "content", nullable = false)
    private String content;//메시지 본문

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type", nullable = false)
    private MessageTypeEnum messageType;

    @Builder
    private ChatMessagesEntity(
        UUID chatMessageId
        , UUID chatRoomId
        , UUID senderId
        , String content
        , MessageTypeEnum messageType
    ) {
        this.chatMessageId = chatMessageId;
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
        this.messageType = messageType;
    }


    public static ChatMessagesEntity create(
        UUID chatMessageId
        , UUID chatRoomId
        , UUID senderId
        , String content
        , MessageTypeEnum messageType
    ) {
        return ChatMessagesEntity.builder()
            .chatMessageId(chatMessageId)
            .chatRoomId(chatRoomId)
            .senderId(senderId)
            .content(content)
            .messageType(messageType)
            .build();
    }
}