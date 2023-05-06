package com.harera.socialnetwork.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.group.Group;

@Repository
public interface GroupRepository extends Neo4jRepository<Group, Long> {

    @Query("MATCH (u:User)-[r :JOINED]->(g:Group) WHERE id(u) = $userId AND id(g) = $groupId DELETE r")
    void leave(@Param("userId") Long userId, @Param("groupId") Long groupId);

    @Query("MATCH (u:User) MATCH (p:Post) WHERE id(u) = 0 AND id(p) = 3 MERGE (u)-[r:HAS_LIKED]->(p)")
    void like(@Param("userId") Long userId, @Param("groupId") Long groupId);

    @Query("MATCH (u:User) MATCH (g:Group) WHERE id(u) = $userId AND id(g) = $groupId MERGE (u)-[r:JOINED]->(g) SET r.datetime = datetime()")
    void join(@Param("userId") Long userId, @Param("groupId") Long groupId);

    @Query("MATCH (u:User) MATCH (g:Group) WHERE id(u) = $userId AND id(g) = $groupId MERGE (u)-[r:FOLLOW]->(g) SET r.datetime = datetime()")
    void follow(@Param("userId") Long userId, @Param("groupId") Long groupId);

    @Query("MATCH (u:User)-[r:JOINED]->(g:Group) WHERE id(g) = $id RETURN id(u)")
    List<Long> findGroupUserIds(Long id);
}
