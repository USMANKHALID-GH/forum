package com.usman.forum.service;


import com.usman.forum.model.Questions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface QuestionService {

    void saveQuestion(Long userId, Questions questions);

    Page<Questions>  findAllQuestion(Pageable pageable, String search);


    Page<Questions> findAllQuestion1(Pageable pageable, String search);

    void deleteQuestion(Long questionId);

    Questions findAQuestion(Long id);

    void updateQuestion(Long questionId, Questions toEntity);
}
