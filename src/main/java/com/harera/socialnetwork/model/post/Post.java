package com.harera.socialnetwork.model.post;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.BaseNodeDto;
import com.harera.socialnetwork.model.user.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Node("Post")
public class Post extends BaseNode {

    @Property("datetime")
    private LocalDateTime datetime;

    @Property("body")
    private String body;

    @Relationship(type = "POSTED", direction = Relationship.Direction.INCOMING)
    private User author;

    @Relationship(type = "SHARED_FROM", direction = Relationship.Direction.OUTGOING)
    private Post sharedPost;

    @Transient
    private int reactsCount;

    @Transient
    private int commentsCount;

    @Transient
    private int sharesCount;
}
