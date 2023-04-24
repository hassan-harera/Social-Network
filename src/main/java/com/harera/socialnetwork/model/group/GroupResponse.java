package com.harera.socialnetwork.model.group;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.BaseNodeDto;
import com.harera.socialnetwork.model.group.follow.GroupFollow;
import com.harera.socialnetwork.model.group.join.GroupJoin;
import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.model.user.UserResponse;

import lombok.Data;

@Data
public class GroupResponse extends BaseNodeDto {

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("name")
    private String name;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("owner")
    private UserResponse owner;

    @JsonProperty("creator")
    private UserResponse creator;
}
