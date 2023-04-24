package com.harera.socialnetwork.model.post.comment;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.post.Post;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@RelationshipProperties
public class Comment extends BaseNode {

    @CreatedDate
    @Property("datetime")
    private LocalDateTime datetime;

    @Property("body")
    private String body;

    @TargetNode
    private User author;
}
