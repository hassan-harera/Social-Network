package com.harera.socialnetwork.model;

import java.io.Serializable;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseNodeDto implements Serializable {

    private Long id;

    private boolean active = true;
}
