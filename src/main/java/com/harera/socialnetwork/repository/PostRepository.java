package com.harera.socialnetwork.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.post.Post;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {

    @Query("MATCH (u:User) MATCH (p:Post) WHERE id(u) = $userId AND id(p) = $postId MERGE (u)-[r:HAS_LIKED]->(p) SET r.datetime = datetime() RETURN p")
    Optional<Post> react(@Param("userId") Long userId, @Param("postId") Long postId);

    @Query("MATCH (p:Post)-[r1]->(a) MATCH (b)-[r2]->(p:Post) WHERE id(p) = $postId DELETE r1, r2; MATCH (b)-[r2]->(p:Post) WHERE id(p) = $postId DELETE r2; MATCH (p:Post) WHERE id(p) = $postId DELETE p")
    void deletePostById(@Param("postId") Long postId);

    @Query("MATCH (p:Post)-[r:HAS_COMMENT]->(c:Comment) WHERE id(p) = $postId RETURN count (c)")
    int countComments(@Param("postId") Long postId);

    @Query("MATCH (p:Post)-[r:HAS_REACT]->(c:React) WHERE id(p) = $postId RETURN count (c)")
    int countReacts(@Param("postId") Long postId);

    @Query("MATCH (p1:Post)-[r:SHARED_FROM]->(p2:Post) WHERE id(p2) = $postId RETURN count (r)")
    int countShares(@Param("postId") Long postId);
}
