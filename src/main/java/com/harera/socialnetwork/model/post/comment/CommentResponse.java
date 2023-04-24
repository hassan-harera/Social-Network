package com.harera.socialnetwork.model.post.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "post", "datetime" })
public class CommentResponse extends CommentDto {

}
