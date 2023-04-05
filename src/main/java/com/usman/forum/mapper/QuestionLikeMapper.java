package com.usman.forum.mapper;

import com.usman.forum.dto.QuestionLikeDto;
import com.usman.forum.model.QuestionLike;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionLikeMapper extends EntityMapper<QuestionLikeDto, QuestionLike>{

    QuestionLike toEntity(QuestionLikeDto dto);
    QuestionLikeDto toDto(QuestionLike entity);
}
