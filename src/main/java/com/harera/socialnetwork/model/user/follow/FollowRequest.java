package com.harera.socialnetwork.model.user.follow;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class FollowRequest {

    @JsonProperty(value = "follower_id")
    private Long followerId;
}
