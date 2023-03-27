package com.usman.forum.mapper;

import com.usman.forum.dto.AnswerDto;
import com.usman.forum.model.Answers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<AnswerDto , Answers> {
    AnswerDto toDto( Answers user);
    Answers toEntity(AnswerDto userDto);
}
