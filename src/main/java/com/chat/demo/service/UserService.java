package com.chat.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.chat.demo.entity.User;
import com.chat.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

// 유저 서비스
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 유저 생성 (회원가입)
    public User createUser(String username, String email, String password) {
        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password)) // 비밀번호 암호화
                .build();
        return userRepository.save(user);
    }

    // 모든 유저 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ID로 유저 조회
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}