package com.usman.forum.mapper;


import com.usman.forum.dto.UserAuthenticateRequestDto;
import com.usman.forum.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticateMapper extends  EntityMapper<UserAuthenticateRequestDto, User>{
    UserAuthenticateRequestDto toDto(User blog);
    User toEntity(UserAuthenticateRequestDto blogDto);
}
