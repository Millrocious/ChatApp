package com.millrocious.chatapp.service.impl;

import com.millrocious.chatapp.entity.chat.ChatMessage;
import com.millrocious.chatapp.exception.WebException;
import com.millrocious.chatapp.repository.ChatMessageRepository;
import com.millrocious.chatapp.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessage findById(Long id) {
        return chatMessageRepository.findById(id)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "Message not found"));
    }

    @Override
    public List<ChatMessage> findAll() {
        return chatMessageRepository.findAll();
    }
}
