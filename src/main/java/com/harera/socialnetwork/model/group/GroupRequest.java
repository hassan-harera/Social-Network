package com.harera.socialnetwork.model.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNodeDto;
import com.harera.socialnetwork.model.page.PageDto;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "id", "active", "datetime", "author" })
public class GroupRequest extends BaseNodeDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("owner_id")
    private long ownerId;
}
