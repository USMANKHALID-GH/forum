package com.usman.forum.repository;


import com.usman.forum.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesReposiory extends JpaRepository<Likes , Long> {

    @Query("from Likes  a where a.answer=:answerId and a.user=:userId")
    Optional<Likes> findLikeByAnmswerAndUser(@Param("answerId")Long answerId , @Param("userId")Long userId);

}
