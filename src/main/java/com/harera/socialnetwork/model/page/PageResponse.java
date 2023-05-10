package com.harera.socialnetwork.model.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class PageResponse extends PageDto {

    @JsonProperty("followers_count")
    private Integer followersCount;

    @JsonProperty("likes_count")
    private Integer likesCount;
}
