package com.chat.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import com.chat.demo.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        log.info("New message: {}", chatMessage.getContent());
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage) {
        chatMessage.setContent(chatMessage.getSender() + "님이 입장했습니다.");
        log.info("User joined: {}", chatMessage.getSender());
        return chatMessage;
    }
}