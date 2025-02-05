package com.chat.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import com.chat.demo.entity.ChatRoom;
import com.chat.demo.service.ChatRoomService;
import java.util.List;
import java.util.Optional;

// 채팅방 컨트롤러
@RestController
@RequestMapping("/chatrooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // 채팅방 생성
    @PostMapping("/create")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestParam String name) {
        ChatRoom newChatRoom = chatRoomService.createChatRoom(name);
        return ResponseEntity.ok(newChatRoom);
    }

    // 모든 채팅방 조회
    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        return ResponseEntity.ok(chatRoomService.getAllChatRooms());
    }

    // 특정 채팅방 조회
    @GetMapping("/{id}")
    public ResponseEntity<ChatRoom> getChatRoomById(@PathVariable Long id) {
        Optional<ChatRoom> chatRoom = chatRoomService.getChatRoomById(id);
        return chatRoom.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
