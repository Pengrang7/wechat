package com.chat.demo.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.chat.demo.entity.User;
import com.chat.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

// 유저 서비스
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // 유저 생성 (회원가입)
    public boolean isUsernameDuplicate(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public User createUser(String username, String email, String password) {
        User user = User.builder()
                .username(username)
                .email(email)
                .password(password)
                .createdAt(LocalDateTime.now())
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

    public User registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }

    // 로그인
    public Optional<User> loginUser(String username, String password) {
        logger.info("로그인 시도 - username: {}", username);
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            logger.info("유저 발견 - username: {}", username);
            if (user.get().getPassword().equals(password)) {
                logger.info("비밀번호 일치 - 로그인 성공");
                return user;
            } else {
                logger.warn("비밀번호 불일치 - 로그인 실패");
            }
        } else {
            logger.warn("유저를 찾을 수 없음 - username: {}", username);
        }
        return Optional.empty();
    }

}