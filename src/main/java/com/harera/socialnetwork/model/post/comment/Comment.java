package com.harera.socialnetwork.model.post.comment;

import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.post.Post;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;

@Data
@Node("Comment")
public class Comment extends BaseNode {

    @Property("comment")
    private String comment;

    @Property("datetime")
    private LocalDateTime datetime;

    @Relationship(type = "HAS_COMMENTED", direction = Relationship.Direction.INCOMING)
    private User user;

    @Relationship(type = "HAS_COMMENT", direction = Relationship.Direction.INCOMING)
    private Post post;
}
