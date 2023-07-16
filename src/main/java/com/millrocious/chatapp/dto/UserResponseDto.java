package com.millrocious.chatapp.dto;


import com.millrocious.chatapp.entity.user.Role;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        Role role
) {
}
