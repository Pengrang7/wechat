package com.chat.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//  기본적인 실시간 채팅 서비스를 구축하기 위한 최소한의 설정입니다. 실제 프로젝트에서는 보안 설정, CORS 설정, 메시지 크기 제한 등을 추가로 고려
@Configuration
@EnableWebSocketMessageBroker  // STOMP 기반 WebSocket 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // 메시지를 전달할 브로커 설정
        registry.setApplicationDestinationPrefixes("/app"); // 클라이언트에서 보낼 메시지 경로 설정
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // WebSocket 연결 엔드포인트 설정
                .setAllowedOrigins("*") // 모든 도메인에서 접근 허용 (보안 강화 필요)
                .withSockJS(); // SockJS 사용
    }
}
