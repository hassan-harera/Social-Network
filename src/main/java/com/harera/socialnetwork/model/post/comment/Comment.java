package com.harera.socialnetwork.model.post.comment;

import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;

import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.post.Post;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;

@Data
@Node
public class Comment extends BaseNode {

    @Property("comment")
    private String comment;

    @Relationship(type = "COMMENTED", direction = Relationship.Direction.INCOMING)
    private User user;
}
