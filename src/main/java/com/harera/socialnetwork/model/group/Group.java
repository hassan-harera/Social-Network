package com.harera.socialnetwork.model.group;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.group.follow.GroupFollow;
import com.harera.socialnetwork.model.group.join.GroupJoin;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;

@Data
@Node("Group")
public class Group extends BaseNode {

    @CreatedDate
    @Property("created_at")
    private LocalDateTime createdAt;

    @Property("name")
    private String name;

    @Property("bio")
    private String bio;

    @Relationship(value = "OWN", direction = Relationship.Direction.INCOMING)
    private User owner;

    @Relationship(value = "CREATED", direction = Relationship.Direction.INCOMING)
    private User creator;

    @Relationship(value = "JOINED", direction = Relationship.Direction.INCOMING)
    private Set<GroupJoin> joined;

    @Relationship(value = "FOLLOW_GROUP", direction = Relationship.Direction.INCOMING)
    private Set<GroupFollow> followers;
}
