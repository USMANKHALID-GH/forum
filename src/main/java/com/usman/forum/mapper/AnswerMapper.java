package com.usman.forum.mapper;

import com.usman.forum.dto.AnswerDto;
import com.usman.forum.model.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper  extends EntityMapper<AnswerDto, Answer> {

    AnswerDto toDto( Answer answers);
    Answer toEntity(AnswerDto answerDto);


}
