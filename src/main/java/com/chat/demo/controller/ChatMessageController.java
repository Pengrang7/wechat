package com.chat.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import com.chat.demo.entity.ChatMessage;
import com.chat.demo.service.ChatMessageService;
import com.chat.demo.service.UserService;
import com.chat.demo.service.ChatRoomService;
import java.util.List;
import java.util.Optional;

import com.chat.demo.entity.User;
import com.chat.demo.entity.ChatRoom;

// 메시지 컨트롤러
@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final UserService userService;
    private final ChatRoomService chatRoomService;

    // 메시지 저장
    @PostMapping("/send")
    public ResponseEntity<ChatMessage> sendMessage(@RequestParam Long userId,
                                                   @RequestParam Long chatRoomId,
                                                   @RequestParam String message) {
        Optional<User> user = userService.getUserById(userId);
        Optional<ChatRoom> chatRoom = chatRoomService.getChatRoomById(chatRoomId);

        if (user.isPresent() && chatRoom.isPresent()) {
            ChatMessage chatMessage = chatMessageService.saveMessage(user.get(), chatRoom.get(), message);
            return ResponseEntity.ok(chatMessage);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // 특정 채팅방의 메시지 조회
    @GetMapping("/chatroom/{chatRoomId}")
    public ResponseEntity<List<ChatMessage>> getMessagesByChatRoom(@PathVariable Long chatRoomId) {
        Optional<ChatRoom> chatRoom = chatRoomService.getChatRoomById(chatRoomId);

        return chatRoom.map(room -> ResponseEntity.ok(chatMessageService.getMessagesByChatRoom(room)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}