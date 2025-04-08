package com.webtoonmaker.api.chat.infra.repositroy;

import com.webtoonmaker.api.chat.application.repository.ChatRoomRepository;
import com.webtoonmaker.api.chat.domain.entity.ChatRoomsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @Repository 안 붙여도 됨
 * Spring Data JPA가 자동으로 프록시 구현체 만들어서 빈 등록해줌
 */
public interface ChatRoomJpaRepository extends JpaRepository<ChatRoomsEntity, UUID>, ChatRoomRepository {
    boolean existsByChatRoomId(UUID chatRoomId);
    boolean existsByChatRoom_ChatRoomIdAndIsDeletedFalse(UUID chatRoomId);


    Optional<ChatRoomsEntity> findByChatRoomId(UUID chatRoomId);

}
