package com.chat.chat_project.chattools;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
    private String content;
    private String sender;
    private MessageType type;
}
