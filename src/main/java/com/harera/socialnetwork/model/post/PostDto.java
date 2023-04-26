package com.harera.socialnetwork.model.post;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNodeDto;
import com.harera.socialnetwork.model.post.comment.Comment;
import com.harera.socialnetwork.model.post.react.React;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostDto extends BaseNodeDto {

    @CreatedDate
    @JsonProperty("datetime")
    private LocalDateTime datetime;

    @JsonProperty("body")
    private String body;

    @JsonProperty("reacts")
    private Set<React> reacts = new HashSet<>();

    @JsonProperty(value = "comments")
    private Set<Comment> comments = new HashSet<>();
}
