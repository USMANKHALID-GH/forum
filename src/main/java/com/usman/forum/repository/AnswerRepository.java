package com.usman.forum.repository;

import com.usman.forum.model.Answers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface AnswerRepository extends JpaRepository<Answers,Long> {

    @Query("select count(a.question) from Answers  a  where a.question.Id=:id")
    Long  numberOfAnsweredQuestion(@Param("id") Long id);

    @Query("from Answers  a  where a.question.Id=:id")
    List<Answers> findAllAnswersByQuestionID(@Param("id") Long id);

}
