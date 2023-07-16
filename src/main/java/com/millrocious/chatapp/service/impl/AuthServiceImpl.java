package com.millrocious.chatapp.service.impl;

import com.millrocious.chatapp.dto.UserRequestDto;
import com.millrocious.chatapp.dto.auth.AuthRequest;
import com.millrocious.chatapp.dto.auth.AuthResponse;
import com.millrocious.chatapp.entity.user.Role;
import com.millrocious.chatapp.entity.user.User;
import com.millrocious.chatapp.exception.WebException;
import com.millrocious.chatapp.mapper.UserMapper;
import com.millrocious.chatapp.repository.UserRepository;
import com.millrocious.chatapp.security.JwtService;
import com.millrocious.chatapp.service.AuthService;
import com.millrocious.chatapp.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserUtil commonUtils;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(UserRequestDto userDto) {
        if(commonUtils.checkIfUserExist(userDto.email())) {
            throw new WebException(HttpStatus.CONFLICT, "User already exists");
        }

        User user = userMapper.toEntity(userDto);

        user.setUserRole(Role.USER);
        commonUtils.encodePassword(user, userDto);
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(
                Map.of(
                        "user_id", user.getId(),
                        "role", user.getUserRole()
                ), user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User user = commonUtils.getExistingUser(request.email());

        String jwtToken = jwtService.generateToken(
                Map.of(
                        "user_id", user.getId(),
                        "role", user.getUserRole()
                ), user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

}
