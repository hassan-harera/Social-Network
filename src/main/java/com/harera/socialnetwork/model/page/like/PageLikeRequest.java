package com.harera.socialnetwork.model.page.like;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties()
public class PageLikeRequest {

    @JsonProperty("user_id")
    private long UserId;
}
