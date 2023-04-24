package com.harera.socialnetwork.model.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.like.LikeDto;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "id", "active", "datetime", "author" })
public class PageRequest extends PageDto {

    @JsonProperty("owner_id")
    private long ownerId;
}
