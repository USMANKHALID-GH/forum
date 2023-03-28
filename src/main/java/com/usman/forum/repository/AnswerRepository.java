package com.usman.forum.repository;

import com.usman.forum.model.Answers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answers,Long> {


}
