package com.harera.socialnetwork.model.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNodeDto;

import lombok.Data;

@Data
public class PageDto extends BaseNodeDto {

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("name")
    private String name;
}
