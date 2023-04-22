package com.harera.socialnetwork.model.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import com.harera.socialnetwork.model.BaseNode;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Node("User")
public class User extends BaseNode {

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

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    private Set<User> followings = new HashSet<>();

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.INCOMING)
    private Set<User> followers = new HashSet<>();
}
