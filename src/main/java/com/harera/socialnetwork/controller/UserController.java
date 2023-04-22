package com.harera.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harera.socialnetwork.model.follow.FollowingRequest;
import com.harera.socialnetwork.model.user.UserRequest;
import com.harera.socialnetwork.model.user.UserResponse;
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

    @PostMapping
    @Operation(summary = "Create", description = "Create a user", tags = "User",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.create(request);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/{id}/followings")
    @Operation(summary = "Follow", description = "Follow another user", tags = "User",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public void follow(@PathVariable("id") Long id,
                    @RequestBody FollowingRequest request) {
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
}
