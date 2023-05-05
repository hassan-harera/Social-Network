package com.harera.socialnetwork.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.user.User;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (u1:User)-[r:FOLLOW_USER]->(u2:User) WHERE id(u2) = $id RETURN u1")
    List<User> findUserFollowersById(@Param("id") Long id);

    @Query("MATCH (u1:User)-[r:FOLLOW_USER]->(u2:User) WHERE id(u1) = $id RETURN u2")
    List<User> findUserFollowingsById(Long id);
}
