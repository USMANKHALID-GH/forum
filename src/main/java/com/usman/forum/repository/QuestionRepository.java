package com.usman.forum.repository;

import com.usman.forum.model.Questions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {

    Page<Questions> findQuestionByTitleContaining(String search, Pageable pageable);
}
