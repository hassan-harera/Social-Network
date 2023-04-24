package com.harera.socialnetwork.model.post.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "post", "datetime" })
public class CommentRequest extends CommentDto {

    @JsonProperty("post_id")
    private long postId;

    @JsonProperty("author_id")
    private long authorId;
}
