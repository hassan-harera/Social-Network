package com.harera.socialnetwork.model.page;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.harera.socialnetwork.model.author.Author;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;

@Data
@Node("Page")
public class Page extends Author {

    @CreatedDate
    @Property("datetime")
    private LocalDateTime datetime;

    @Property("name")
    private String name;

    @Property("followers_count")
    private Integer followersCount = 0;

    @Property("likes_count")
    private Integer likesCount = 0;

    @Relationship(value = "OWN", direction = Relationship.Direction.INCOMING)
    private User owner;

    @Relationship(value = "CREATED", direction = Relationship.Direction.INCOMING)
    private User creator;
}
