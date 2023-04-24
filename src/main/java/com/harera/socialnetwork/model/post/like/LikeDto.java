package com.harera.socialnetwork.model.post.like;

import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;

@Data
public class LikeDto extends BaseNode {

    @JsonProperty("datetime")
    private LocalDateTime datetime;

    @JsonProperty("author")
    private User author;
}
