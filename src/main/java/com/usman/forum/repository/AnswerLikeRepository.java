package com.usman.forum.repository;


import com.usman.forum.model.AnswerLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerLikeRepository extends JpaRepository<AnswerLike, Long> {

    @Query("from AnswerLike  a where a.answer.Id=:answerId and a.user.Id=:userId")
    Optional<AnswerLike> findAnswerLikedByUser(@Param("answerId")Long answerId , @Param("userId")Long userId);

    @Query("SELECT  COUNT(a.answer.Id) FROM  AnswerLike  a where  a.answer.Id=:id")
    Integer findAnswerLikeCount(@Param("id") Long id);

}
