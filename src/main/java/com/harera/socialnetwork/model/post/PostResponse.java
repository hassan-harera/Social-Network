package com.harera.socialnetwork.model.post;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNodeDto;
import com.harera.socialnetwork.model.post.comment.Comment;
import com.harera.socialnetwork.model.post.react.React;
import com.harera.socialnetwork.model.user.UserResponse;

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
    private UserResponse author;

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
