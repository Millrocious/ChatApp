package com.millrocious.chatapp.controller.user;

import com.millrocious.chatapp.dto.UserRequestDto;
import com.millrocious.chatapp.dto.auth.AuthRequest;
import com.millrocious.chatapp.dto.auth.AuthResponse;
import com.millrocious.chatapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/authenticate")
    ResponseEntity<AuthResponse> auth(@RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN') or isAnonymous()")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid UserRequestDto userDto) {
        return ResponseEntity.ok(authService.register(userDto));
    }
}
