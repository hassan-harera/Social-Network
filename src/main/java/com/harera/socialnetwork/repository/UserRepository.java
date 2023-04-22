package com.harera.socialnetwork.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.harera.socialnetwork.model.user.User;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

}
