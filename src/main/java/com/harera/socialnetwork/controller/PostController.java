package com.harera.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harera.socialnetwork.model.comment.CommentRequest;
import com.harera.socialnetwork.model.like.LikeRequest;
import com.harera.socialnetwork.model.post.PostRequest;
import com.harera.socialnetwork.model.post.PostResponse;
import com.harera.socialnetwork.model.user.UserResponse;
import com.harera.socialnetwork.service.PostService;
import com.harera.socialnetwork.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Post", description = "Post API")
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @PostMapping
    @Operation(summary = "Posting", description = "Create a post", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<PostResponse> create(@RequestBody PostRequest request) {
        PostResponse userResponse = postService.create(request);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Comment", description = "Comment a comment", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public void comment(@PathVariable("id") Long id,
                    @RequestBody CommentRequest request) {
        postService.comment(id, request);
    }

    @PostMapping("/{id}/likes")
    @Operation(summary = "Like", description = "Like a post", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public void like(@PathVariable("id") Long id,
                    @RequestBody LikeRequest request) {
        postService.like(id, request);
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
