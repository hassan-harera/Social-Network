package com.harera.socialnetwork.model.user.follow;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Data
@RelationshipProperties
public class UserFollow extends BaseNode {

    @Property("datetime")
    private LocalDateTime dateTime;

    @TargetNode
    private User user;
}
