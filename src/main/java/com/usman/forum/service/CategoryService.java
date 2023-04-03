package com.usman.forum.service;

import com.usman.forum.model.Questions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<Questions>  findQuestionByCat(String search, Pageable pageable);
}
