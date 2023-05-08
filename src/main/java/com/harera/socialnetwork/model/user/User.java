package com.harera.socialnetwork.model.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.harera.socialnetwork.model.author.Author;
import com.harera.socialnetwork.model.user.follow.UserFollow;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Node("User")
public class User extends Author {

    @Property("username")
    private String username;

    @Property("mobile")
    private String mobile;

    @Property("first_name")
    private String firstName;

    @Property("last_name")
    private String lastName;

    @Property("password")
    private String password;

    @Property("image_url")
    private String imageUrl;

    @Property("bio")
    private String bio;

    @Property("email")
    private String email;

    @Relationship(type = "FOLLOW_USER", direction = Relationship.Direction.OUTGOING)
    private Set<UserFollow> followings = new HashSet<>();

    @Relationship(type = "FOLLOW_USER", direction = Relationship.Direction.INCOMING)
    private Set<UserFollow> followers = new HashSet<>();

    public String getName() {
        return firstName + " " + lastName;
    }
}
