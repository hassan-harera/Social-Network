package com.harera.socialnetwork.model.group.follow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties()
public class GroupFollowRequest {

    @JsonProperty("user_id")
    private long UserId;
}
