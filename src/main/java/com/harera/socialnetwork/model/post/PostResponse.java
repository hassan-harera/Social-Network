package com.harera.socialnetwork.model.post;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNodeDto;
import com.harera.socialnetwork.model.author.AuthorResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostResponse extends BaseNodeDto {

    @JsonProperty("datetime")
    private LocalDateTime datetime;

    @JsonProperty("body")
    private String body;

    @JsonProperty("shared_post")
    private PostResponse sharedPost;

    @JsonProperty("author")
    private AuthorResponse author;

    @JsonProperty("reacts_count")
    private int reactsCount;

    @JsonProperty("comments_count")
    private int commentsCount;

    @JsonProperty("shares_count")
    private int sharesCount;

    public boolean getIsSharingPost() {
        return sharedPost != null;
    }
}
