package com.harera.socialnetwork.model.post;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequest implements Serializable {

    @JsonProperty("datetime")
    private LocalDateTime datetime;

    @JsonProperty("body")
    private String body;

    @JsonProperty("reacts_count")
    private int reactsCount;

    @JsonProperty("comments_count")
    private int commentsCount;

    @JsonProperty("shares_count")
    private int sharesCount;

    @JsonProperty("author_id")
    Long authorId;
}
