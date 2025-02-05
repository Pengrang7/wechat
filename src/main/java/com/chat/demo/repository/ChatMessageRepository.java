package com.chat.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chat.demo.entity.ChatMessage;
import com.chat.demo.entity.ChatRoom;

import java.util.List;

// 메시지 저장소
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoom(ChatRoom chatRoom);  // 채팅방별 메시지 조회
}