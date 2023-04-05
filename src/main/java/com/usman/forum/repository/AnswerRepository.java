package com.usman.forum.repository;

import com.usman.forum.model.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {

    @Query("select count(a.question) from Answer  a  where a.question.Id=:id")
    Long  numberOfAnsweredQuestion(@Param("id") Long id);

    @Query("select a from Answer  a  where a.question.Id=:id")
    List<Answer> findAllAnswersByQuestionID(@Param("id") Long id);

    @Query("from Answer  a where a.question.Id=:questionId and a.user.Id=:userId")
    Optional<Answer> findAnswerByQuestionAndUser(@Param("questionId")Long questionId , @Param("userId")Long userId);


}
