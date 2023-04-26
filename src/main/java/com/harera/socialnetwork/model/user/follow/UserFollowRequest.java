package com.harera.socialnetwork.model.user.follow;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserFollowRequest {

    @JsonProperty(value = "follower_id")
    private Long followerId;
}
