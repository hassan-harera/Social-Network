package com.harera.socialnetwork.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.post.Post;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {

    @Query("MATCH (u:User) MATCH (p:Post) WHERE id(u) = $userId AND id(p) = $postId MERGE (u)-[r:LIKED]->(p) SET r.datetime = datetime() RETURN p")
    Optional<Post> like(@Param("userId") Long userId, @Param("postId") Long postId);

    @Query("MATCH (u:User)-[r:LIKED]->(p:Post) WHERE id(u) = $userId AND id(p) = $postId DELETE r RETURN p")
    Optional<Post> unlike(@Param("userId") Long userId, @Param("postId") Long postId);
}
