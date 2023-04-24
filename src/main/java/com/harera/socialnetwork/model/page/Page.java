package com.harera.socialnetwork.model.page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;

@Data
@Node("Page")
public class Page extends BaseNode {

    @CreatedDate
    @Property("datetime")
    private LocalDateTime datetime;

    @Property("name")
    private String name;

    @Relationship(value = "OWN", direction = Relationship.Direction.INCOMING)
    private User owner;

    @Relationship(value = "FOLLOW", direction = Relationship.Direction.INCOMING)
    private Set<User> followers;

    @Relationship(value = "LIKE", direction = Relationship.Direction.INCOMING)
    private Set<User> likes;
}
