package com.harera.socialnetwork.model.like;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "id")
@RelationshipProperties
public class Like extends BaseNode {

    @CreatedDate
    @Property("datetime")
    private LocalDateTime datetime;

    @TargetNode
    private User author;
}
