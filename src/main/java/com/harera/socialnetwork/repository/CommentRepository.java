package com.harera.socialnetwork.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.post.comment.Comment;

@Repository
public interface CommentRepository extends Neo4jRepository<Comment, Long> {

    @Query("MATCH (c:Comment)-[r:ACTED_ON]->(p:Post) WHERE id(p) = $postId RETURN c")
    List<Comment> listComments(@Param("postId") Long postId);

    @Query("MATCH (c:Comment)-[ra:ACTED_ON]->(p:Post) MATCH (u:User)-[rc:COMMENTED]->(c) WHERE id(p) = $postId AND id(c) = $commentId DELETE ra, rc, c")
    void deleteComment(@Param("postId") Long postId,
                    @Param("commentId") Long commentId);
}
