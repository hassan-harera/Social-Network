package com.harera.socialnetwork.model.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNodeDto;

import lombok.Data;

@Data
public class AuthorDto extends BaseNodeDto {

    @JsonProperty("name")
    private String name;
}
