package com.harera.socialnetwork.model.post.share;

import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;

@Data
@RelationshipProperties
public class PostShare extends BaseNode {

    @Property("datetime")
    private LocalDateTime datetime;

    @Property("caption")
    private String caption;

    @TargetNode
    private User author;
}
