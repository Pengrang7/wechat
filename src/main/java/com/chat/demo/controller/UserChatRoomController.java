package com.chat.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import com.chat.demo.entity.UserChatRoom;
import com.chat.demo.service.UserChatRoomService;
import com.chat.demo.service.UserService;
import com.chat.demo.service.ChatRoomService;
import java.util.List;
import java.util.Optional;
import com.chat.demo.entity.User;
import com.chat.demo.entity.ChatRoom;

// 유저 채팅방 컨트롤러
@RestController
@RequestMapping("/user-chatrooms")
@RequiredArgsConstructor
public class UserChatRoomController {

    private final UserChatRoomService userChatRoomService;
    private final UserService userService;
    private final ChatRoomService chatRoomService;

    // 유저가 채팅방 참가
    @PostMapping("/join")
    public ResponseEntity<UserChatRoom> joinChatRoom(@RequestParam Long userId, @RequestParam Long chatRoomId) {
        Optional<User> user = userService.getUserById(userId);
        Optional<ChatRoom> chatRoom = chatRoomService.getChatRoomById(chatRoomId);

        if (user.isPresent() && chatRoom.isPresent()) {
            UserChatRoom userChatRoom = userChatRoomService.joinChatRoom(user.get(), chatRoom.get());
            return ResponseEntity.ok(userChatRoom);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // 특정 유저가 참가한 채팅방 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserChatRoom>> getChatRoomsByUser(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);

        return user.map(u -> ResponseEntity.ok(userChatRoomService.getChatRoomsByUser(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}