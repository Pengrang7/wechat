package com.chat.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.chat.demo.entity.ChatMessage;
import com.chat.demo.entity.ChatRoom;
import com.chat.demo.entity.User;
import com.chat.demo.repository.ChatMessageRepository;

import lombok.RequiredArgsConstructor;

// 메시지 서비스
@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    // 메시지 저장
    public ChatMessage saveMessage(User user, ChatRoom chatRoom, String message) {
        ChatMessage chatMessage = ChatMessage.builder()
                .user(user)
                .chatRoom(chatRoom)
                .message(message)
                .build();
        return chatMessageRepository.save(chatMessage);
    }

    // 특정 채팅방의 메시지 조회
    public List<ChatMessage> getMessagesByChatRoom(ChatRoom chatRoom) {
        return chatMessageRepository.findByChatRoom(chatRoom);
    }
}