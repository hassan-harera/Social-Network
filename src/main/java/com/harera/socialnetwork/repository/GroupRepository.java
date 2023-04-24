package com.harera.socialnetwork.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.group.Group;
import com.harera.socialnetwork.model.page.Page;

@Repository
public interface GroupRepository extends Neo4jRepository<Group, Long> {
}
