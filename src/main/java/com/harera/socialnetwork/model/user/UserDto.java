package com.harera.socialnetwork.model.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNode;
import com.harera.socialnetwork.model.BaseNodeDto;

import lombok.Data;

@Data
public class UserDto extends BaseNodeDto {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("email")
    private String email;

    @JsonProperty("mobile")
    private String mobile;
}
