package com.usman.forum.mapper;

import com.usman.forum.dto.SubAnswerDto;

import com.usman.forum.model.SubAnswers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubAnswerMapper extends EntityMapper<SubAnswerDto, SubAnswers>{
    SubAnswerDto toDto( SubAnswers subAnswers);
    SubAnswers toEntity(SubAnswerDto subAnswerDto);
}
