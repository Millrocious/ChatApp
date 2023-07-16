package com.millrocious.chatapp.service;

import com.millrocious.chatapp.entity.chat.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    ChatMessage findById(Long id);
    List<ChatMessage> findAll();
}
