package com.usman.forum.service;


import com.usman.forum.model.SubAnswers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface SubAnswerService {
    void saveSubAnswer(SubAnswers answers , Long uid, Long qid);


    void updateSubAnswer(SubAnswers toEntity, Long id);

    void deleteSubAnswer(Long id);

    Page<SubAnswers> findAllSubAnswer(Pageable pageable);

    SubAnswers findSubAnswer(Long id);
}
