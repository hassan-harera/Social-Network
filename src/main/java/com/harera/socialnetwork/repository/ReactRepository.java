package com.harera.socialnetwork.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.post.comment.Comment;
import com.harera.socialnetwork.model.post.react.React;

@Repository
public interface ReactRepository extends Neo4jRepository<React, Long> {

    @Query("MATCH (c:React)-[r:ACTED_ON]->(p:Post) WHERE id(p) = $postId RETURN c")
    List<Comment> listReacts(@Param("postId") Long postId);

    @Query("MATCH (u:User)-[r2:HAS_REACTED]->(re:React) MATCH (p:Post)-[r1:HAS_REACT]->(re:React) WHERE id(p) = $postId AND id(u) = $userId DELETE r1, r2, re")
    void deleteReact(@Param("postId") Long postId, @Param("userId") Long userId);

    @Query("MATCH (u:User)-[r1:HAS_REACTED]->(re:React) MATCH (p:Post)-[r2:HAS_REACT]->(re:React) WHERE id(u) = $userId AND id(p) = $postId RETURN re")
    Optional<React> findByUserIdAndPostId(@Param("userId") Long userId,
                    @Param("postId") Long postId);

    @Query("MATCH (p:Post)-[r2:HAS_REACT]->(re:React) WHERE id(p) = $postId RETURN id(re)")
    List<Long> findAllIdsByPostId(Long postId);
}
