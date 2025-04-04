package com.webtoonmaker.api.chat.application.service;

import com.webtoonmaker.api.chat.application.dto.UserDto;
import com.webtoonmaker.api.chat.application.repository.UsersRepository;
import com.webtoonmaker.api.chat.domain.entity.UsersEntity;
import com.webtoonmaker.api.chat.presentation.response.UserResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //생성
    public UserResponseDto createUser(UserDto dto) {
        // email 중복 확인
        if (usersRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + dto.getEmail());
        }

        // 서비스 레이어에서 UUID 생성
        final UUID userId = dto.getUserId() != null ? dto.getUserId() : UUID.randomUUID();
        dto.createId(userId);

        // 비밀번호 암호화
        final String encodedPassword = passwordEncoder.encode(dto.getPassword());

        UsersEntity user = UsersEntity.create(
            dto.getUserId(),
            dto.getEmail(),
            encodedPassword,
            dto.getUserName(),
            dto.getPhoneNumber(),
            dto.getRole(),
            dto.getZipCode(),
            dto.getAddress1(),
            dto.getAddress2()
        );
        return UserResponseDto.fromUser(usersRepository.save(user));
    }

    //조회
    public UserResponseDto getUser(UUID userId) {
        UsersEntity user = getUserEntity(userId);
        return UserResponseDto.fromUser(user);
    }

    public UsersEntity getUserEntity(UUID userId) {
        return usersRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    //수정
    public UserResponseDto updateUser(UUID userId, UserDto dto) {
        UsersEntity user = getUserEntity(userId);
        user.updateUserInfo(
            dto.getUserName(),
            dto.getPhoneNumber(),
            dto.getRole()
        );
        user.updateAddress(dto.getZipCode(), dto.getAddress1(), dto.getAddress2());
        return UserResponseDto.fromUser(user);
    }

    //삭제
    public void deleteUser(UUID userId) {
        UsersEntity user = getUserEntity(userId);
        user.setDeleted(true);
    }
}
