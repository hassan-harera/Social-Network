package com.harera.socialnetwork.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.post.Post;
import com.harera.socialnetwork.model.post.comment.Comment;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {

    @Query("MATCH (u:User) MATCH (p:Post) WHERE id(u) = $userId AND id(p) = $postId MERGE (u)-[r:LIKED]->(p) SET r.datetime = datetime() RETURN p")
    Optional<Post> like(@Param("userId") Long userId, @Param("postId") Long postId);

    @Query("MATCH (u:User)-[r:LIKED]->(p:Post) WHERE id(u) = $userId AND id(p) = $postId DELETE r RETURN p")
    Optional<Post> deleteLike(@Param("userId") Long userId, @Param("postId") Long postId);

    @Query("MATCH (u:User) MATCH (p:Post) WHERE id(u) = $userId AND id(p) = $postId MERGE (u)-[r:COMMENTED]->(p) SET r.datetime = datetime() SET r.comment = $comment RETURN p")
    Optional<Post> comment(@Param("userId") Long userId, @Param("postId") Long postId,
                    @Param("comment") String comment);

    @Query("MATCH (u:User)-[r:COMMENTED]->(p:Post) WHERE id(p) = $postId AND id(r) = $commentId DELETE r RETURN p")
    Optional<Post> deleteComment(Long postId, Long commentId);
}
