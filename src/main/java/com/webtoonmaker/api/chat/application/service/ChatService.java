package com.webtoonmaker.api.chat.application.service;

import com.webtoonmaker.api.chat.application.dto.ChatDto;
import com.webtoonmaker.api.chat.application.dto.ChatMessageDto;
import com.webtoonmaker.api.chat.application.dto.ChatRoomDto;
import com.webtoonmaker.api.chat.application.dto.ChatRoomParticipantDto;
import com.webtoonmaker.api.chat.domain.entity.UsersEntity;
import com.webtoonmaker.api.chat.domain.enums.ParticipantStatusEnum;
import com.webtoonmaker.api.chat.infra.kafka.ChatMessageProducer;
import com.webtoonmaker.api.chat.infra.utils.JsonConverter;
import com.webtoonmaker.api.chat.presentation.response.ChatMessageResponseDto;
import com.webtoonmaker.api.chat.presentation.response.ChatRoomResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final UserService userService;
    private final ChatRoomService chatRoomService;
    private final ChatRoomParticipantService chatRoomParticipantService;
    private final ChatMessageService chatMessageService;

    private final ChatMessageProducer chatMessageProducer;
    private final JsonConverter jsonConverter;

    public ChatRoomResponseDto createChatRoom(ChatDto dto) {
        //채팅방 생성
        ChatRoomResponseDto chatRoom = chatRoomService.createChatRoom(dto.toChatRoomDto());

        List<UUID> userList = dto.getUserList();

        // 참여자 추가, 중간에 오류나도 계속해서 진행
        for (UUID userId : userList) {
            try {
                chatRoomParticipantService.join(
                    ChatRoomParticipantDto.of(
                        null
                        , chatRoom.getChatRoomId()
                        , userId
                        , ParticipantStatusEnum.JOINED
                    )
                );
            } catch (Exception e) {
                log.error("참여자 추가 실패 - userId: " + userId + " : " + e.getMessage());
            }
        }

        return chatRoom;
    }

    public List<ChatRoomDto> getParticipantRooms(UUID userId) {
        return chatRoomParticipantService.getListParticipantRooms(userId);
    }

    @Transactional
    public boolean sendMessage(ChatMessageDto dto) {
        //채팅방 존재하는지 체크 -> ChatRoomService
        if (!chatRoomService.isValidByChatRoomId(dto.getChatRoomId())) {
            return false;
        }

        //참여자가 맞는지 체크
        if (!chatRoomParticipantService.isActiveParticipantInRoom(dto.getChatRoomId(), dto.getSenderId())) {
            return false;
        }

        //채팅 메세지 생성 -> ChatMessageService -> DB 저장
        //저장된 메시지를 반환받아서 Kafka로 전송하는 게 명확한 흐름
        ChatMessageResponseDto chatMessageResponseDto = chatMessageService.create(dto.getChatRoomId(), dto);

        // 메세지 전파: 카프카 메세지 발행 -> chatKafkaProducer
        String jsonKafkaChatMessage = jsonConverter.toJson(chatMessageResponseDto.toKafka());
        chatMessageProducer.sendMessage("chat-messages", chatMessageResponseDto.getChatRoomId().toString(), jsonKafkaChatMessage);
        return true;
    }

    public boolean isValid(UUID chatRoomId, UUID userId) {
        if (!chatRoomService.isValidByChatRoomId(chatRoomId)) {
            return false;
        }
        UsersEntity userEntity = userService.getUserEntity(userId);

        return chatRoomParticipantService.isActiveParticipantInRoom(chatRoomId, userEntity.getUserId());
    }


}
