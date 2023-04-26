package com.harera.socialnetwork.model.post.react;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.user.UserResponse;

import lombok.Data;

@Data
public class ReactResponse implements Serializable {

    @JsonProperty("datetime")
    private LocalDateTime datetime;

    @JsonProperty("type")
    private React.Type type;

    @JsonProperty("user")
    private UserResponse user;
}
