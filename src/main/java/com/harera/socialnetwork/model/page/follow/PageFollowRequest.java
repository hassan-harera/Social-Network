package com.harera.socialnetwork.model.page.follow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties()
public class PageFollowRequest {

    @JsonProperty("user_id")
    private long UserId;
}
