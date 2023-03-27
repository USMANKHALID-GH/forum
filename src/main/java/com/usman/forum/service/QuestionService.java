package com.usman.forum.service;

import com.usman.forum.dto.QuestionToShowDto;
import com.usman.forum.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface QuestionService {

    void saveQuestion(Long userId, Question question);

    Page<Question>  findAllQuestion(Pageable pageable,String search);


    Page<Question> findAllQuestion1(Pageable pageable, String search);
}
