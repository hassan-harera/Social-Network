package com.harera.socialnetwork.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNode;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto extends BaseNode {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String username;

    private String password;

    @JsonProperty("image_url")
    private String imageUrl;

    private String bio;

    private String email;

    @JsonProperty("mobile")
    private String mobile;
}
