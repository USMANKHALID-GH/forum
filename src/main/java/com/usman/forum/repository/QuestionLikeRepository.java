package com.usman.forum.repository;


import com.usman.forum.model.QuestionLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuestionLikeRepository extends JpaRepository<QuestionLike , Long> {

 @Query("from QuestionLike  a where a.question.Id=:answerId and a.user.Id=:userId")
 Optional<QuestionLike> likeUnlikeQuestion(@Param("answerId")Long answerId , @Param("userId")Long userId);

 @Query("SELECT  COUNT(a.question.Id) FROM  QuestionLike  a where  a.question.Id=:id")
 Integer findQuestionLikeCount(@Param("id") Long id);


}
