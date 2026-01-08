package com.chat.chat_project.chattools;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller // web controller will return an html view with templates, @RestController returns JSON
public class ChatController {
    // usually handle a single endpoint (send a chat message)
    @MessageMapping("/chat.sendMessage") // when we send a message to this url we run this method
    @SendTo("/topic/public") // the topic broker will recieve the result and broadcast it to everyone
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    // JOIN handler
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor // this class lets you use STOMP headers, WebSoc session attribs
    ) {
        // Add username in WebSocket session
        headerAccessor.getSessionAttributes()
                .put("username", chatMessage.getSender());

        return chatMessage;
    }
}
