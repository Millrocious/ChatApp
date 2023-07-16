package com.millrocious.chatapp.service;

import com.millrocious.chatapp.dto.UserRequestDto;
import com.millrocious.chatapp.dto.auth.AuthRequest;
import com.millrocious.chatapp.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse register(UserRequestDto user);
    AuthResponse authenticate(AuthRequest user);
}
