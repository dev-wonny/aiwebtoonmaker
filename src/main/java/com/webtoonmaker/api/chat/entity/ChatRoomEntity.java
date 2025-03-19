package com.webtoonmaker.api.chat.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "p_chat_rooms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
public class ChatRoomEntity extends BaseEntity {
    @Id
    @Column(name = "chatroom_id", columnDefinition = "UUID", nullable = false, unique = true)
    private UUID chatRoomId;

    @Column(name = "chatroom_name", length = 50, nullable = false)
    private String chatRoomName;

    @Column(name = "is_groupchat", nullable = false)
    private boolean isGroupChat = false;

    @OneToMany(mappedBy = "ChatRoomEntity", cascade = CascadeType.REMOVE)
    private List<ChatParticipantEntity> chatParticipants = new ArrayList<>();

    @OneToMany(mappedBy = "ChatRoomEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChatMessageEntity> chatMessages = new ArrayList<>();
}