package com.harera.socialnetwork.model.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.harera.socialnetwork.model.BaseNode;

import lombok.Data;

@Data
public class UserDto extends BaseNode {

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

    @JsonProperty("followers")
    private List<UserResponse> followerList;

    @JsonProperty("followings")
    private List<UserResponse> followingList;
}
