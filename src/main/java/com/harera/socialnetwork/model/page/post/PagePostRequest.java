package com.harera.socialnetwork.model.page.post;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class PagePostRequest {

    @JsonProperty("body")
    private String body;
}
