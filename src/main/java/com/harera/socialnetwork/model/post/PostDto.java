package com.harera.socialnetwork.model.post;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNodeDto;
import com.harera.socialnetwork.model.post.comment.Comment;
import com.harera.socialnetwork.model.post.like.Like;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;

@Setter
@Getter
public class PostDto extends BaseNodeDto {

    @CreatedDate
    @JsonProperty("datetime")
    private LocalDateTime datetime;

    @JsonProperty("body")
    private String body;

    @JsonProperty("likes")
    private Set<Like> likes = new HashSet<>();

    @JsonProperty(value = "comments")
    private Set<Comment> comments = new HashSet<>();
}
