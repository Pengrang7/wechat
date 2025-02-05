package com.chat.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chat.demo.entity.UserChatRoom;


// 유저-채팅방 관계 저장소
@Repository
public interface UserChatRoomRepository extends JpaRepository<UserChatRoom, Long> {
}