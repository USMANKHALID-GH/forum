package com.usman.forum.mapper;


import com.usman.forum.dto.UserDto;
import com.usman.forum.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);

}
