package com.usman.forum.mapper;

import com.usman.forum.dto.QuestionDto;
import com.usman.forum.model.Questions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper extends EntityMapper<QuestionDto, Questions>{
    QuestionDto toDto( Questions questions);
    Questions toEntity(QuestionDto questionDto);
}
