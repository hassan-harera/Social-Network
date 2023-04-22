package com.harera.socialnetwork.model.post;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
@Getter
public class PostRequest extends PostDto {

    @JsonProperty("author_id")
    Long authorId;
}
