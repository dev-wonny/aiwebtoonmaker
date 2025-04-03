package com.webtoonmaker.api.chat.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/*
단순히 "이 사람은 이 방에서 어느 메시지까지 읽었는지"만 저장
읽음 처리 부담이 줄어듦 (특히 단체방에서 수백 개 메시지 읽을 때)
MongoDB
 */

@Entity
@Table(name = "p_chat_room_read_cursors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChatRoomReadCursorEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "chat_room_read_cursor_id", updatable = false, nullable = false, unique = true)
    private UUID chatRoomReadCursorId;// PK

    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoomsEntity chatRooms;//FK

    @ManyToOne
    @JoinColumn(name = "chat_message_id", nullable = false)
    private ChatMessagesEntity lastReadMessageId;//FK

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;// FK
}
