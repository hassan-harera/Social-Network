package com.harera.socialnetwork.model.like;

import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.user.User;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "id", "active", "datetime", "author" })
public class LikeRequest extends LikeDto {

    @JsonProperty("author_id")
    private long authorId;
}
