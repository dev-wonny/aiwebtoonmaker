package com.webtoonmaker.api.chat.infra.repositroy;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtoonmaker.api.chat.domain.entity.ChatMessagesEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * 클래스 이름에 반드시 Impl 붙여야 Spring Data가 자동 인식함
 * 사용자 정의 QueryDSL 구현체 → 이름 중요
 */
@Repository
public class ChatMessageRepositoryCustomImpl implements ChatMessageCustomRepository {// 여기서 지금 save 구현하라고 컴파일 오류남

    private final JPAQueryFactory queryFactory;
//    private final QChatMessagesEntity chat = QChatMessagesEntity.chatMessagesEntity;

    public ChatMessageRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ChatMessagesEntity> findRecentMessagesByRoomId(UUID roomId, int limit) {
        return null;
//            queryFactory
//            .selectFrom(chat)
//            .where(chat.chatRoomId.eq(roomId)
//                .and(chat.isDeleted.isFalse()))
//            .orderBy(chat.createdAt.desc())
//            .limit(limit)
//            .fetch();
    }
}
