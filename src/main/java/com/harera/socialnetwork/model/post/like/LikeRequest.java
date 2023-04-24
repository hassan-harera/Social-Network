package com.harera.socialnetwork.model.post.like;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "id", "active", "datetime", "author" })
public class LikeRequest extends LikeDto {

    @JsonProperty("author_id")
    private long authorId;
}
