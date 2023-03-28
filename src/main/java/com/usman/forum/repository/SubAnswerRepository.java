package com.usman.forum.repository;

import com.usman.forum.model.SubAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubAnswerRepository  extends JpaRepository<SubAnswers, Long> {
}
