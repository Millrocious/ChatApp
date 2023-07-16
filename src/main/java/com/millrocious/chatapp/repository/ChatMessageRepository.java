package com.millrocious.chatapp.repository;

import com.millrocious.chatapp.entity.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
