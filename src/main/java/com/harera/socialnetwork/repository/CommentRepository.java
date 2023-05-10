package com.harera.socialnetwork.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.post.comment.Comment;

@Repository
public interface CommentRepository extends Neo4jRepository<Comment, Long> {

    @Query("MATCH (p:Post)-[r:HAS_COMMENT]->(c:Comment) WHERE id(p) = $postId RETURN id(c)")
    List<Long> listCommentIdsByPostId(@Param("postId") Long postId);

    @Query("MATCH (p:Post)-[ra:HAS_COMMENT]->(c:Comment) MATCH (u:User)-[rc:HAS_COMMENTED]->(c) WHERE id(p) = $postId AND id(c) = $commentId DELETE ra, rc, c")
    void deleteComment(@Param("postId") Long postId, @Param("commentId") Long commentId);
}
