package com.millrocious.chatapp.dto.auth;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token
) {
}
