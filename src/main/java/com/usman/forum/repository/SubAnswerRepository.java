package com.usman.forum.repository;

import com.usman.forum.model.Answers;
import com.usman.forum.model.SubAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubAnswerRepository  extends JpaRepository<SubAnswers, Long> {

    @Query("from SubAnswers   a where a.answer.Id=:answerId and a.user.Id=:userId")
    Optional<SubAnswers> findSubAnswerByQuestionAndUser(@Param("answerId")Long answerId, @Param("userId") Long userId);
}
