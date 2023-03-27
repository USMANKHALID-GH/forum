package com.usman.forum.mapper;


import com.usman.forum.dto.QuestionToShowDto;
import com.usman.forum.model.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionsToshowMapper extends EntityMapper<QuestionToShowDto, Question> {

    QuestionToShowDto toDto(Question question);
    Question toEntity(QuestionToShowDto questionDto);
}
