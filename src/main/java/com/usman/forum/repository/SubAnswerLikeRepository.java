package com.usman.forum.repository;

import com.usman.forum.model.QuestionLike;
import com.usman.forum.model.SubAnswerLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubAnswerLikeRepository extends JpaRepository<SubAnswerLike , Long> {
    @Query("from SubAnswerLike  a where a.subAnswer.Id=:answerId and a.user.Id=:userId")
    Optional<SubAnswerLike> likeUnlikeSubAnswer(@Param("answerId")Long answerId , @Param("userId")Long userId);

    @Query("SELECT  COUNT(a.subAnswer.Id) FROM  SubAnswerLike   a where  a.subAnswer.Id=:id")
    Integer findSubAnswerLikeCount(@Param("id") Long id);
}
