package com.harera.socialnetwork.model.post;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequest extends PostDto {

    @JsonProperty("author_id")
    Long authorId;
}
