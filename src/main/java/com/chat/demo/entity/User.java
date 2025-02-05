package com.chat.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 유저 ID (PK)

    @Column(nullable = false, unique = true, length = 50)
    private String username; // 유저 이름

    @Column(nullable = false)
    private String password; // 비밀번호 (해시 저장)

    @Column(nullable = false, unique = true, length = 100)
    private String email; // 이메일

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 가입 날짜
}
