package com.webtoonmaker.api.chat.domain.documents;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "chat_room_read_cursors")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomReadCursorDocument {

    @MongoId
    private String id;

    @Field("chat_room_id")
    private UUID chatRoomId;

    @Field("chat_message_id")
    private UUID lastReadMessageId;

    @Field("user_id")
    private UUID userId;

    @Field("read_at")
    private LocalDateTime readAt;

    @Builder
    public ChatRoomReadCursorDocument(UUID chatRoomId, UUID lastReadMessageId, UUID userId, LocalDateTime readAt) {
        this.chatRoomId = chatRoomId;
        this.lastReadMessageId = lastReadMessageId;
        this.userId = userId;
        this.readAt = readAt;
    }
}