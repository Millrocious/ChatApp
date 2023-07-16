package com.millrocious.chatapp.config;

import com.millrocious.chatapp.entity.chat.ChatMessage;
import com.millrocious.chatapp.entity.chat.MessageType;
import com.millrocious.chatapp.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageTemplate;
    private final ChatMessageRepository chatMessageRepository;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setContent("");
            chatMessage.setSender(username);
            chatMessage.setType(MessageType.LEAVE);

            chatMessageRepository.save(chatMessage);

            messageTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
