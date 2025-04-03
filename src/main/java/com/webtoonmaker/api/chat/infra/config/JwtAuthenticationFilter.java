//package com.webtoonmaker.api.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private final JwtUtil jwtUtil;
//
//    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    // JWT 인증을 적용하여 로그인한 사용자만 채팅 가능
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//        throws ServletException, IOException {
//        String token = request.getHeader("Authorization");
//        if (token != null && token.startsWith("Bearer ")) {
//            String username = jwtUtil.getUsername(token.substring(7));
//            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()));
//        }
//        chain.doFilter(request, response);
//    }
//}
