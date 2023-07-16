package com.millrocious.chatapp.controller.chat;

import com.millrocious.chatapp.entity.chat.ChatMessage;
import com.millrocious.chatapp.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat-messages")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @GetMapping
    public List<ChatMessage> getAllChatMessages() {
        return chatMessageService.findAll();
    }
}

