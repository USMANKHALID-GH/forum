package com.usman.forum.mapper;

import com.usman.forum.dto.CategoryDto;
import com.usman.forum.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper  extends EntityMapper<CategoryDto,Category> {

    Category toEntity(CategoryDto dto);
    CategoryDto toDto(Category entity);
}
