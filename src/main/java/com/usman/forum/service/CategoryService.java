package com.usman.forum.service;

import com.usman.forum.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<Question>  findQuestionByCat(String search, Pageable pageable);
}
