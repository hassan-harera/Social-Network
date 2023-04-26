package com.harera.socialnetwork.model.page;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNodeDto;
import com.harera.socialnetwork.model.user.UserResponse;

import lombok.Data;

@Data
public class PageDto extends BaseNodeDto {

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("name")
    private String name;

    @JsonProperty("owner")
    private UserResponse owner;

    @JsonProperty("followers")
    private List<UserResponse> followers;

    @JsonProperty("likes")
    private List<UserResponse> likes;
}
