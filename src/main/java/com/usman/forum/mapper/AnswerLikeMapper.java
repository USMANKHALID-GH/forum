package com.usman.forum.mapper;

import com.usman.forum.dto.AnswerLikeDto;

import com.usman.forum.model.AnswerLike;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerLikeMapper extends  EntityMapper<AnswerLikeDto, AnswerLike>{
    AnswerLike toEntity(AnswerLikeDto dto);
    AnswerLikeDto toDto( AnswerLike entity);
}
