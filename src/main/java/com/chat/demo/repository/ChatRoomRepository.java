package com.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chat.entity.ChatRoom;


// 채팅방 저장소
@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}