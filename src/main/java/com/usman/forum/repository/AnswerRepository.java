package com.usman.forum.repository;

import com.usman.forum.model.Answers;

import com.usman.forum.model.Questions;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AnswerRepository extends JpaRepository<Answers,Long> {

    @Query("select count(a.question) from Answers  a  where a.question.Id=:id")
    Long  numberOfAnsweredQuestion(@Param("id") Long id);

    @Query("select a from Answers  a  where a.question.Id=:id")
    List<Answers> findAllAnswersByQuestionID(@Param("id") Long id);

    @Query("from Answers  a where a.question.Id=:questionId and a.user.Id=:userId")
    Optional<Answers> findAnswerByQuestionAndUser(@Param("questionId")Long questionId , @Param("userId")Long userId);


}
