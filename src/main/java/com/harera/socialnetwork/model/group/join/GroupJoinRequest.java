package com.harera.socialnetwork.model.group.join;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties()
public class GroupJoinRequest {

    @JsonProperty("user_id")
    private long UserId;
}
