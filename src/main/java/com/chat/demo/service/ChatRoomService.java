package com.chat.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.chat.demo.entity.ChatRoom;
import com.chat.demo.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

// 채팅방 서비스
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    // 채팅방 생성
    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .build();
        return chatRoomRepository.save(chatRoom);
    }

    // 모든 채팅방 조회
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    // ID로 채팅방 조회
    public Optional<ChatRoom> getChatRoomById(Long id) {
        return chatRoomRepository.findById(id);
    }
}
