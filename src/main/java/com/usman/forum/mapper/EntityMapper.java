package com.usman.forum.mapper;

import com.usman.forum.dto.BaseDto;
import com.usman.forum.model.AbstractModel;

import java.util.List;


public interface EntityMapper <D extends BaseDto , E extends AbstractModel>{

    E toEntity(D dto);
    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);
    List<D> toDto(List<E> entityList);
}
