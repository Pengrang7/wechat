package com.chat.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 유저 ID (PK)

    @Column(name = "username", unique = true, length = 50)
    private String username; // 유저 이름

    @Column(name = "password")
    private String password; // 비밀번호 (해시 저장)

    @Column(unique = true, length = 100)
    private String email; // 이메일

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // 가입 날짜
}
