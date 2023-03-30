package com.usman.forum.service;

import com.usman.forum.model.Answers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface AnswersService {

    void saveAnswer(Answers answers , Long uid, Long qid);

    Page<Answers> findAllAnswer(Pageable pageable);

    Answers findAnswer(Long id);

    List<Answers> searchForAnswersByQuestion(Pageable pageable, String saerch);

    void deleteAnswer(Long id,Long questionId);

    void bestAnswer(Long id,Long questionId);


}
