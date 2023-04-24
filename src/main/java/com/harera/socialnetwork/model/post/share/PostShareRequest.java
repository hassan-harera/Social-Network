package com.harera.socialnetwork.model.post.share;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PostShareRequest {

    @JsonProperty("caption")
    private String caption;

    @JsonProperty("author_id")
    private long authorId;
}
