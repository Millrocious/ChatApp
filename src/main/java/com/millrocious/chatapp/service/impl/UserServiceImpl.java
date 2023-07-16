package com.millrocious.chatapp.service.impl;

import com.millrocious.chatapp.dto.UserRequestDto;
import com.millrocious.chatapp.dto.UserResponseDto;
import com.millrocious.chatapp.entity.user.User;
import com.millrocious.chatapp.mapper.UserMapper;
import com.millrocious.chatapp.repository.UserRepository;
import com.millrocious.chatapp.service.UserService;
import com.millrocious.chatapp.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUtil userUtil;

    @Override
    public UserResponseDto get(Long id) {
        User user = userUtil.getExistingUser(id);
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userDto) {
        User user = userUtil.getExistingUser(id);
        userMapper.updateEntity(userDto, user);
        userRepository.save(user);
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }
}
