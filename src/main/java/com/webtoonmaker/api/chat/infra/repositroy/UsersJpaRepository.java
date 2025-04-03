package com.webtoonmaker.api.chat.infra.repositroy;

import com.webtoonmaker.api.chat.application.repository.UsersRepository;
import com.webtoonmaker.api.chat.domain.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @Repository 안 붙여도 됨
 * Spring Data JPA가 자동으로 프록시 구현체 만들어서 빈 등록해줌
 */
public interface UsersJpaRepository extends JpaRepository<UsersEntity, UUID>, UsersRepository {
    boolean existsByEmail(String email);

    Optional<UsersEntity> findByEmail(String email);
}