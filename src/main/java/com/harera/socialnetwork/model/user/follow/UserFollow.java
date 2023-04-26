package com.harera.socialnetwork.model.user.follow;

import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;

@Data
@RelationshipProperties
public class UserFollow extends BaseNode {

    @Property("datetime")
    private LocalDateTime dateTime;

    @TargetNode
    private User user;
}
