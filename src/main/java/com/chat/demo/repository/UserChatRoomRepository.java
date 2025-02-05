package com.chat.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chat.demo.entity.UserChatRoom;
import com.chat.demo.entity.User;

import java.util.List;

// 유저-채팅방 관계 저장소
@Repository
public interface UserChatRoomRepository extends JpaRepository<UserChatRoom, Long> {
    List<UserChatRoom> findByUser(User user);  // 특정 유저가 속한 채팅방 조회
}