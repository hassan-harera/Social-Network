package com.harera.socialnetwork.model.post.comment;

import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.BaseNodeDto;
import com.harera.socialnetwork.model.post.Post;
import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.model.user.UserResponse;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CommentDto extends BaseNodeDto {

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("user")
    private UserResponse user;
}
