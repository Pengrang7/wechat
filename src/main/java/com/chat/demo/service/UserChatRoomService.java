package com.chat.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.chat.demo.entity.UserChatRoom;
import com.chat.demo.entity.User;
import com.chat.demo.entity.ChatRoom;
import com.chat.demo.repository.UserChatRoomRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

// 유저-채팅방 관계 서비스
@Service
@RequiredArgsConstructor
public class UserChatRoomService {

    private final UserChatRoomRepository userChatRoomRepository;

    // 유저가 채팅방에 참가
    public UserChatRoom joinChatRoom(User user, ChatRoom chatRoom) {
        UserChatRoom userChatRoom = UserChatRoom.builder()
                .user(user)
                .chatRoom(chatRoom)
                .build();
        return userChatRoomRepository.save(userChatRoom);
    }

    // 특정 유저가 참가한 채팅방 조회
    public List<UserChatRoom> getChatRoomsByUser(User user) {
        return userChatRoomRepository.findByUser(user);
    }
}