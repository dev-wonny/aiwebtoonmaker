package com.webtoonmaker.api.chat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Getter
@Entity
@Table(name = "p_chat_participants")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
public class ChatParticipantEntity extends BaseEntity {

    @Id
    @Column(name = "chat_participant_id", columnDefinition = "UUID", nullable = false, unique = true)
    private UUID chatParticipantId;

    @ManyToOne
    @JoinColumn(name = "chatroom_id", nullable = false)
    private ChatRoomEntity chatRoomId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;
}