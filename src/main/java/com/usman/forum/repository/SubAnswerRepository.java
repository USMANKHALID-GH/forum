package com.usman.forum.repository;

import com.usman.forum.model.Answers;
import com.usman.forum.model.Questions;
import com.usman.forum.model.SubAnswers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface SubAnswerRepository  extends JpaRepository<SubAnswers, Long> {

    @Query("from SubAnswers   a where a.answer.Id=:answerId and a.user.Id=:userId")
    Optional<SubAnswers> findSubAnswerByQuestionAndUser(@Param("answerId")Long answerId, @Param("userId") Long userId);


//    @Query("select ans.answer.question from   SubAnswers  ans where ans.content=:search or ans.answer.content=:search or ans.answer.question.content=:search")
//    Page<Questions> searchAllInQuestionOrAnswersOrSubAnswers(@Param("search") String search, Pageable pageable);


    @Query("select ans.answer.question from   SubAnswers  ans where ans.content like %:search% or ans.answer.content LIKE %:search% or ans.answer.question.content LIKE %:search%")
    Page<Questions> searchAllInQuestionOrAnswersOrSubAnswers(@Param("search") String search, Pageable pageable);

}
