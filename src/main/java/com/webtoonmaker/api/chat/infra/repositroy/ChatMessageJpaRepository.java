package com.webtoonmaker.api.chat.infra.repositroy;

import com.webtoonmaker.api.chat.application.repository.ChatMessageRepository;
import com.webtoonmaker.api.chat.domain.entity.ChatMessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @Repository 안 붙여도 됨
 * Spring Data JPA가 자동으로 프록시 구현체 만들어서 빈 등록해줌
 * Spring Data JPA + 자동 구현
 */
//@Primary
public interface ChatMessageJpaRepository extends JpaRepository<ChatMessagesEntity, UUID>, ChatMessageRepository, ChatMessageCustomRepository {
}