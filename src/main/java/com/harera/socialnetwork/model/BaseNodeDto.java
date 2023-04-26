package com.harera.socialnetwork.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseNodeDto implements Serializable {

    @JsonProperty("id")
    private Long identity;

    private boolean active = true;
}
