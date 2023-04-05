package com.usman.forum.mapper;

import com.usman.forum.dto.QuestionDto;
import com.usman.forum.model.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper extends EntityMapper<QuestionDto, Question>{
    QuestionDto toDto( Question questions);
    Question toEntity(QuestionDto questionDto);
}
