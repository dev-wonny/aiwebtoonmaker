package com.webtoonmaker.api.chat.domain.entity;

import static com.webtoonmaker.api.chat.domain.enums.ParticipantStatusEnum.JOINED;
import static com.webtoonmaker.api.chat.domain.enums.ParticipantStatusEnum.LEFT;

import com.webtoonmaker.api.chat.domain.enums.ParticipantStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

/**
 * 채팅방 하나당 유저 수만큼 row가 늘어남
 * "채팅방"과 "유저"는 다대다(M:N) 관계
 * 유저가 속한 채팅방 목록
 * 채팅방의 전체 유저 조회
 * 유저가 채팅방에 존재하는지 체크
 * 채팅방 나가기
 */
@Entity
@Table(name = "p_chat_room_participants")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class ChatRoomParticipantsEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "chat_room_participant_id", updatable = false, nullable = false, unique = true)
    private UUID chatRoomParticipantId;//PK

    //주인, 저장O
    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoomsEntity chatRoom;//FK

    //주인, 저장O
    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private UsersEntity participant;//FK

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ParticipantStatusEnum status;//퇴장 여부

    @Builder
    private ChatRoomParticipantsEntity(
        UUID chatRoomParticipantId
        , ChatRoomsEntity chatRoom
        , UsersEntity participant
        , ParticipantStatusEnum status
    ) {
        this.chatRoomParticipantId = chatRoomParticipantId;
        this.chatRoom = chatRoom;
        this.participant = participant;
        this.status = status;
    }

    public static ChatRoomParticipantsEntity create(
        ChatRoomsEntity chatRoom
        , UsersEntity user
        , ParticipantStatusEnum status
    ) {
        return ChatRoomParticipantsEntity.builder()
            .chatRoom(chatRoom)
            .participant(user)
            .status(status)
            .build();
    }

    public void updateActive(ParticipantStatusEnum status) {
        this.status = status;
    }

    public void joinRoom() {
        this.status = JOINED;
    }

    public void leaveRoom() {
        this.status = LEFT;
    }

}