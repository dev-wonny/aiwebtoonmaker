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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
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
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
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
    private ChatTypeEnum roomType;

    //종속, 저장X
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    private List<ChatRoomParticipantsEntity> chatParticipants = new ArrayList<>();

    //종속, 저장X
//    @OneToMany(mappedBy = "ChatMessages", cascade = CascadeType.REMOVE, orphanRemoval = true)//?? 이건 무슨관계지
//    private List<ChatMessagesEntity> chatMessages = new ArrayList<>();

    @Builder
    private ChatRoomsEntity(
        UUID chatRoomId
        , String chatRoomName
        , UsersEntity ownerId
        , ChatTypeEnum roomType
        , List<ChatRoomParticipantsEntity> chatParticipants
//        , List<ChatMessagesEntity> chatMessages
    ) {
        this.chatRoomId = chatRoomId;
        this.chatRoomName = chatRoomName;
        this.ownerId = ownerId;
        this.roomType = roomType;
        this.chatParticipants = chatParticipants;
//        this.chatMessages = chatMessages;
    }

    public static ChatRoomsEntity create(
        String chatRoomName
        , UsersEntity ownerId
        , ChatTypeEnum roomType
    ) {
        return ChatRoomsEntity.builder()
            .chatRoomName(chatRoomName)
            .ownerId(ownerId)
            .roomType(roomType)
            .build();
    }

    public void updateChatRoomInfo(
        UUID chatRoomId
        , String chatRoomName
        , UsersEntity ownerId
        , ChatTypeEnum roomType
    ) {
        this.chatRoomId = chatRoomId;
        this.chatRoomName = chatRoomName;
        this.ownerId = ownerId;
        this.roomType = roomType;
    }
}