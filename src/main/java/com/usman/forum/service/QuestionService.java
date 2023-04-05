package com.usman.forum.service;


import com.usman.forum.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface QuestionService {

    void saveQuestion(Long userId, Question questions);

    Page<Question>  findAllQuestion(Pageable pageable, String search);


    Page<Question> findAllQuestion1(Pageable pageable, String search);

    void deleteQuestion(Long userID ,Long questionId);

    Question findAQuestion(Long id);

    void updateQuestion(Long questionId,Long userID , Question toEntity);

    String likeUnlikeQuestion(Long userId, Long questionId);


    Integer questionLikeCount(long id);
}
