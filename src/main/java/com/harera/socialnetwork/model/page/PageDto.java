package com.harera.socialnetwork.model.page;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.BaseNodeDto;
import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.model.user.UserDto;
import com.harera.socialnetwork.model.user.UserResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class PageDto extends BaseNodeDto {

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("name")
    private String name;

    @JsonProperty("owner")
    private UserResponse owner;

    @JsonProperty("followers")
    private List<UserResponse> followers;
}
