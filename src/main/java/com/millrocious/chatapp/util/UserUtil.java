package com.millrocious.chatapp.util;

import com.millrocious.chatapp.dto.UserRequestDto;
import com.millrocious.chatapp.entity.user.User;
import com.millrocious.chatapp.exception.WebException;
import com.millrocious.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void encodePassword(User userEntity, UserRequestDto user){
        userEntity.setPassword(passwordEncoder.encode(user.password()));
    }

    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User getExistingUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User getExistingUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "User not found"));
    }
}