package com.usman.forum.mapper;


import com.usman.forum.dto.SubAnswerLikeDto;

import com.usman.forum.model.SubAnswerLike;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubAnswerLikeMapper  extends EntityMapper<SubAnswerLikeDto,SubAnswerLike>{


    SubAnswerLike toEntity( SubAnswerLikeDto dto);
    SubAnswerLikeDto toDto( SubAnswerLike entity);
}
