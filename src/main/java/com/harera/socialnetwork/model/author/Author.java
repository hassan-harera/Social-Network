package com.harera.socialnetwork.model.author;

import com.harera.socialnetwork.model.BaseNode;

import lombok.Data;

import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.core.schema.Property;

@Data
public class Author extends BaseNode {

    @Transient
    private String name;

    @Property("profile_picture")
    private String profilePicture;
}
