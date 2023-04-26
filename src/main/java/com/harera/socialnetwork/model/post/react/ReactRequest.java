package com.harera.socialnetwork.model.post.react;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReactRequest implements Serializable {

    @Valid
    @NotNull
    @JsonProperty("author_id")
    private long authorId;

    @Valid
    @NotNull
    @JsonProperty("type")
    private React.Type type;
}
