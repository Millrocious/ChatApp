package com.millrocious.chatapp.service;

import com.millrocious.chatapp.dto.UserRequestDto;
import com.millrocious.chatapp.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto get(Long id);
    UserResponseDto update(Long id, UserRequestDto user);
    void delete(Long id);
    List<UserResponseDto> getAll();
}
