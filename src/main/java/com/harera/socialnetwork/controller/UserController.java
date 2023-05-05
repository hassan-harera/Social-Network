package com.harera.socialnetwork.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harera.socialnetwork.model.post.PostResponse;
import com.harera.socialnetwork.model.user.UserRequest;
import com.harera.socialnetwork.model.user.UserResponse;
import com.harera.socialnetwork.model.user.follow.UserFollowRequest;
import com.harera.socialnetwork.service.PostService;
import com.harera.socialnetwork.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User", description = "User API")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping
    @Operation(summary = "Create", description = "Create a user", tags = "User",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.create(request);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/{id}/follow")
    @Operation(summary = "Follow", description = "Follow another user", tags = "User",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public void follow(@PathVariable("id") Long id,
                    @RequestBody UserFollowRequest request) {
        userService.follow(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get", description = "Get a user", tags = "User",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<UserResponse> get(@PathVariable("id") Long id) {
        UserResponse userResponse = userService.get(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}/followers")
    @Operation(summary = "List followers", description = "List user followers by id",
                    tags = "User", responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<List<UserResponse>> listFollowers(@PathVariable("id") Long id) {
        List<UserResponse> followers = userService.listFollowers(id);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/{id}/followings")
    @Operation(summary = "List followings", description = "List user followings by id",
                    tags = "User", responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<List<UserResponse>> listFollowings(@PathVariable("id") Long id) {
        List<UserResponse> followers = userService.listFollowings(id);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/{id}/feed")
    @Operation(summary = "Get Feed", description = "Get feed posts",
                    tags = "User", responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<List<PostResponse>> feed(@PathVariable("id") Long id) {
        List<PostResponse> posts = postService.getFeedPosts(id);
        return ResponseEntity.ok(posts);
    }
}
