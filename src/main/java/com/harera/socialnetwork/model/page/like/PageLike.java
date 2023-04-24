
package com.harera.socialnetwork.model.page.like;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import lombok.Data;

import java.time.LocalDateTime;

import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.user.User;

@Data
@RelationshipProperties
public class PageLike extends BaseNode {

    @Property("datetime")
    private LocalDateTime dateTime;

    @TargetNode
    private User user;
}
