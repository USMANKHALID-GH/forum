package com.usman.forum.service;

import com.usman.forum.model.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface AnswersService {

    void saveAnswer(Answer answers , Long uid, Long qid);

    Page<Answer> findAllAnswer(Pageable pageable);

    Answer findAnswer(Long id);

    List<Answer> searchForAnswersByQuestion(Pageable pageable, String search);

    void deleteAnswer( Long userID, Long id,Long questionId);

    void bestAnswer(Long userID,Long id,Long questionId);


    String likeUnlikeAnswer(Long userI, Long answerId);

    Integer answerLikeCount(long id);
}
