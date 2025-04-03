package com.webtoonmaker.api.chat.application.repository;

import com.webtoonmaker.api.chat.domain.entity.UsersEntity;

import java.util.Optional;
import java.util.UUID;

/**
 * DDD Style
 * Application : UsersRepository
 * Infrastructure : UsersJpaRepository
 * 분리
 */
public interface UsersRepository {
    UsersEntity save(UsersEntity entity);

    boolean existsByEmail(String email);

    Optional<UsersEntity> findById(UUID userId);

    Optional<UsersEntity> findByEmail(String email);

}
