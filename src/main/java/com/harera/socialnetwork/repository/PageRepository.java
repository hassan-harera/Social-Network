package com.harera.socialnetwork.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.page.Page;
import com.harera.socialnetwork.service.PageService;

@Repository
public interface PageRepository extends Neo4jRepository<Page, Long> {

    @Query("MATCH (u:User) MATCH (p:Page) WHERE id(u) = $userId AND id(p) = $pageId MERGE (u)-[r:FOLLOW]->(p) SET r.datetime = datetime()")
    void mergeFollowRelationByIdAndUserId(@Param("pageId") Long pageId, @Param("userId") long userId);

    @Query("MATCH (u:User)-[r:FOLLOW]->(p:Page) WHERE id(u) = $userId AND id(p) = $pageId DELETE r")
    void deleteFollowRelationByIdAndUserId(@Param("pageId") Long pageId, @Param("userId") Long userId);

    @Query("MATCH (u:User)-[r:FOLLOW]->(p:Page) WHERE id(u) = $userId AND id(p) = $pageId RETURN COUNT(r)")
    int countFollowRelationByIdAndUserId(@Param("pageId") Long id, @Param("userId") Long userId);

    @Query("MATCH (u:User) MATCH (p:Page) WHERE id(u) = $userId AND id(p) = $pageId MERGE (u)-[r:LIKED]->(p) SET r.datetime = datetime()")
    void mergeLikeRelationByIdAndUserId(@Param("pageId") Long pageId, @Param("userId") long userId);

    @Query("MATCH (u:User)-[r:LIKED]->(p:Page) WHERE id(u) = $userId AND id(p) = $pageId DELETE r")
    void deleteLikeRelationByIdAndUserId(@Param("pageId") Long id, @Param("userId") Long userId);

    @Query("MATCH (u:User)-[r:LIKED]->(p:Page) WHERE id(u) = $userId AND id(p) = $pageId RETURN COUNT(r)")
    int countLikeRelationByIdAndUserId(@Param("pageId") Long id, @Param("userId") Long userId);

    @Query("MATCH (pa:Page)-[r:POSTED]->(po:Post) WHERE id(pa) = $id RETURN id(po)")
    List<Long> listPostIds(@Param("id") Long id);

    @Query("MATCH (pa:Page) MATCH (po:Post) WHERE id(pa) = $pageId AND id(po) = $postId MERGE (pa)-[r:POSTED]->(po) SET r.datetime = datetime()")
    PageService mergePostRelationByIdAndPostId(@Param("pageId") Long pageId, @Param("postId") Long postId);

    @Query("MATCH (n1)-[r1]->(p:Page) MATCH (p:Page)-[r2]->(n2)  WHERE id(p) = $id DELETE r1, r2")
    void deleteRelations(Long id);

    @Query("MATCH (u:User)-[r:FOLLOW]->(p:Page) WHERE id(p) = $id RETURN COUNT(r)")
    Optional<Integer> countFollowers(Long id);

    @Query("MATCH (u:User)-[r:FOLLOW]->(p:Page) WHERE id(p) = $id RETURN id(u)")
    List<Long> listFollowerIds(Long id);
}
