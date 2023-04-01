package com.usman.forum.service;


import com.usman.forum.model.Questions;
import com.usman.forum.model.SubAnswers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SubAnswerService {
    void saveSubAnswer(SubAnswers answers , Long uid, Long qid);


    void updateSubAnswer(Long userId,SubAnswers toEntity, Long id);

    void deleteSubAnswer(Long userId,Long id);

    Page<SubAnswers> findAllSubAnswer(Pageable pageable);

    SubAnswers findSubAnswer(Long id);
    Page<Questions> searchAllInQuestionOrAnswersOrSubAnswer( String search, Pageable pageable);
}
