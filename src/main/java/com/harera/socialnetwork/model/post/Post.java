package com.harera.socialnetwork.model.post;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.comment.Comment;
import com.harera.socialnetwork.model.like.Like;
import com.harera.socialnetwork.model.user.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Node("Post")
public class Post extends BaseNode {

    @CreatedDate
    @Property("datetime")
    private LocalDateTime datetime;

    @Property("body")
    private String body;

    @Relationship(type = "POSTED", direction = Relationship.Direction.INCOMING)
    private User author;

    @Relationship(type = "LIKED", direction = Relationship.Direction.INCOMING)
    private Set<Like> likes = new HashSet<>();

    @Relationship(type = "COMMENTED", direction = Relationship.Direction.INCOMING)
    private Set<Comment> comments = new HashSet<>();
}
