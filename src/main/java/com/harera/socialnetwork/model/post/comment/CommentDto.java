package com.harera.socialnetwork.model.post.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNodeDto;
import com.harera.socialnetwork.model.user.UserResponse;

import lombok.Data;

@Data
public class CommentDto extends BaseNodeDto {

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("user")
    private UserResponse user;
}
