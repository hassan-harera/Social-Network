package com.harera.socialnetwork.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.page.Page;
import com.harera.socialnetwork.service.PageService;

@Repository
public interface PageRepository extends Neo4jRepository<Page, Long> {

    @Query("MATCH (u:User) MATCH (p:Page) WHERE id(u) = $userId AND id(p) = $pageId MERGE (u)-[r:FOLLOW]->(p) SET r.datetime = datetime()")
    void follow(@Param("pageId") Long pageId, @Param("userId") long userId);

    @Query("MATCH (u:User) MATCH (p:Page) WHERE id(u) = $userId AND id(p) = $pageId MERGE (u)-[r:LIKED]->(p) SET r.datetime = datetime()")
    void like(@Param("pageId") Long pageId, @Param("userId") long userId);

    @Query("MATCH (pa:Page)-[r:POSTED]->(po:Post) WHERE id(pa) = $id")
    List<Long> listPostIds(Long id);

    @Query("MATCH (pa:Page) MATCH (po:Post) WHERE id(pa) = $pageId AND id(po) = $postId MERGE (pa)-[r:POSTED]->(po) SET r.datetime = datetime()")
    PageService post(@Param("pageId") Long pageId, @Param("postId") Long postId);

    @Query("MATCH (n1)-[r1]->(p:Page) MATCH (p:Page)-[r2]->(n2)  WHERE id(p) = $id DELETE r1, r2")
    void deleteRelations(Long id);
}
