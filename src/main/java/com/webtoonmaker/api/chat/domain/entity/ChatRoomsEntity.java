package com.webtoonmaker.api.chat.domain.entity;

import com.webtoonmaker.api.chat.domain.enums.ChatTypeEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "p_chat_rooms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class ChatRoomsEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "chat_room_id", updatable = false, nullable = false, unique = true)
    private UUID chatRoomId;


    @Column(name = "chat_room_name", length = 50, nullable = false)
    private String chatRoomName;

    // 주인, 저장O
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UsersEntity ownerId;//FK

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private ChatTypeEnum room_type;

    //종속, 저장X
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    private List<ChatRoomParticipantsEntity> chatParticipants = new ArrayList<>();

    //종속, 저장X
    @OneToMany(mappedBy = "ChatMessagesEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChatMessagesEntity> chatMessages = new ArrayList<>();
}