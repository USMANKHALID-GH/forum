package com.usman.forum.mapper;

import com.usman.forum.dto.SubAnswerDto;

import com.usman.forum.model.SubAnswer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubAnswerMapper extends EntityMapper<SubAnswerDto, SubAnswer>{
    SubAnswerDto toDto( SubAnswer subAnswer);
    SubAnswer toEntity(SubAnswerDto subAnswerDto);
}
