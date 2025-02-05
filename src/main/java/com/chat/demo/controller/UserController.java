package com.chat.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;

import com.chat.demo.entity.User;
import com.chat.demo.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

// 유저 컨트롤러
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 생성 (회원가입)
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> userData) {
        String username = userData.get("username");
        String email = userData.get("email");
        String password = userData.get("password");

        try {
            if (userService.isUsernameDuplicate(username)) {
                return ResponseEntity.status(409).body("중복된 nickname입니다"); // 409 Conflict
            }

            userService.createUser(username, email, password);
            return ResponseEntity.ok("회원가입 성공");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body("중복된 nickname입니다"); // 409 Conflict
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류가 발생했습니다."); // 500 Internal Server Error
        }
    }

    // 모든 유저 조회
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // ID로 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        Optional<User> user = userService.loginUser(username, password);
        return user.map(u -> ResponseEntity.ok("hello"))
                   .orElseGet(() -> ResponseEntity.status(401).body("존재하지 않는 회원 정보입니다."));
    }
    
}