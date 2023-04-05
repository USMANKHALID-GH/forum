package com.usman.forum.repository;

import com.usman.forum.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    Page<Question> findQuestionByTitleContaining(String search, Pageable pageable);

    @Query("from  Question q where q.category.name like  %:search%")
    Page<Question> findQuestionByCat(@Param("search") String search, Pageable pageable);




}
