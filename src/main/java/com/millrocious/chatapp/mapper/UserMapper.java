package com.millrocious.chatapp.mapper;

import com.millrocious.chatapp.dto.UserRequestDto;
import com.millrocious.chatapp.dto.UserResponseDto;
import com.millrocious.chatapp.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toUserResponseDto(User user);
    User toEntity(UserRequestDto userDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(UserRequestDto userDto, @MappingTarget User user);
}
