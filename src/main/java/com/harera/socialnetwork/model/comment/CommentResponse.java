package com.harera.socialnetwork.model.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "post", "datetime" })
public class CommentResponse extends CommentDto {

}
