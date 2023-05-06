package com.harera.socialnetwork.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.page.Page;

@Repository
public interface PageRepository extends Neo4jRepository<Page, Long> {

    @Query("MATCH (u:User) MATCH (p:Page) WHERE id(u) = $userId AND id(p) = $pageId MERGE (u)-[r:FOLLOW]->(p) SET r.datetime = datetime()")
    void follow(@Param("pageId") Long pageId, @Param("userId") long userId);

    @Query("MATCH (u:User) MATCH (p:Page) WHERE id(u) = $userId AND id(p) = $pageId MERGE (u)-[r:LIKED]->(p) SET r.datetime = datetime()")
    void like(@Param("pageId") Long pageId, @Param("userId") long userId);
}
