package com.webtoonmaker.api.chat.infra.config;

import com.webtoonmaker.api.chat.application.service.ChatService;
import io.jsonwebtoken.Claims;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class StompHandler implements ChannelInterceptor {

    private final ChatService chatService;
    private final JwtUtil jwtUtil;

    public StompHandler(ChatService chatService, JwtUtil jwtUtil) {
        this.chatService = chatService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT == accessor.getCommand()) {
            System.out.println("connect요청시 토큰 유효성 검증");
            String bearerToken = accessor.getFirstNativeHeader("Authorization");
            String token = bearerToken.substring(7);
            jwtUtil.getClaims(token);
        }
        if (StompCommand.SUBSCRIBE == accessor.getCommand()) {
            System.out.println("subscribe 검증");
            String bearerToken = accessor.getFirstNativeHeader("Authorization");
            String token = bearerToken.substring(7);
            Claims claims = jwtUtil.getClaims(token);
            String email = claims.getSubject();
            String roomId = accessor.getDestination().split("/")[2];
            if (!chatService.isRoomPaticipant(email, Long.parseLong(roomId))) {
                throw new AuthenticationServiceException("해당 room에 권한이 없습니다.");
            }
        }

        return message;
    }


}
