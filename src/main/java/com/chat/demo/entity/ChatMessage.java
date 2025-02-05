package com.chat.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 메시지 ID (PK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id", nullable = false)
    private ChatRoom chatRoom; // 어떤 채팅방인지 (ChatRoom.id)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 메시지를 보낸 유저 (User.id)

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message; // 메시지 내용

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp = LocalDateTime.now(); // 보낸 시간
}