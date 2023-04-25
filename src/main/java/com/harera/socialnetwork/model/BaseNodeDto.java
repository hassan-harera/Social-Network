package com.harera.socialnetwork.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
@Getter
public abstract class BaseNodeDto implements Serializable {

    @JsonProperty("id")
    private Long identity;

    private boolean active = true;
}
