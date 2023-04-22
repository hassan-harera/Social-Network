package com.harera.socialnetwork.model.follow;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class FollowingRequest {

    @JsonProperty(value = "follower_id")
    private Long followerId;
}
