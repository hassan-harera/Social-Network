package com.harera.socialnetwork.model.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNodeDto;

import lombok.Data;

@Data
public class AuthorResponse extends AuthorDto {

    @JsonProperty("name")
    private String name;
}
