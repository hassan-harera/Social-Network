package com.harera.socialnetwork.model.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNodeDto;
import com.harera.socialnetwork.model.user.UserResponse;

import lombok.Data;

@Data
public class GroupResponse extends BaseNodeDto {

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("name")
    private String name;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("owner")
    private UserResponse owner;

    @JsonProperty("creator")
    private UserResponse creator;
}
