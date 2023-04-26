package com.harera.socialnetwork.model.post.react;

import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.post.Post;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;

@Data
@Node("React")
public class React extends BaseNode {

    @Property("datetime")
    private LocalDateTime datetime;

    @Property("type")
    private Type type;

    @Relationship(type = "HAS_REACTED", direction = Relationship.Direction.INCOMING)
    private User user;

    @Relationship(type = "HAS_REACT", direction = Relationship.Direction.INCOMING)
    private Post post;

    public enum Type {
        LIKE,
        LOVE,
        HAHA,
        ANGRY,
        CARE
    }
}
