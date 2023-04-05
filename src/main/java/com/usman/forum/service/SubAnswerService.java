package com.usman.forum.service;


import com.usman.forum.model.Question;
import com.usman.forum.model.SubAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SubAnswerService {
    void saveSubAnswer(SubAnswer answers , Long uid, Long qid);


    void updateSubAnswer(Long userId, SubAnswer toEntity, Long id);

    void deleteSubAnswer(Long userId,Long id);

    Page<SubAnswer> findAllSubAnswer(Pageable pageable);

    SubAnswer findSubAnswer(Long id);
    Page<Question> searchAllInQuestionOrAnswersOrSubAnswer(String search, Pageable pageable);




    String likeUnlikeSubAnswer(Long userId, Long subAnswerId);

    Integer subAnswerLikeCount(long id);
}
