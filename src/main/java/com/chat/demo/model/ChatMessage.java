package com.chat.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String sender;  // 보낸 사람 (유저명)
    private String content; // 메시지 내용
    private MessageType type; // 메시지 타입 (입장, 채팅, 퇴장)

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }
}