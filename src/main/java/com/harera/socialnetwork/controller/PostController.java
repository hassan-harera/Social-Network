package com.harera.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harera.socialnetwork.model.post.PostRequest;
import com.harera.socialnetwork.model.post.PostResponse;
import com.harera.socialnetwork.model.post.comment.CommentRequest;
import com.harera.socialnetwork.model.post.comment.CommentResponse;
import com.harera.socialnetwork.model.post.like.LikeRequest;
import com.harera.socialnetwork.model.post.share.PostShareRequest;
import com.harera.socialnetwork.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@Tag(name = "Post", description = "Post API")
@RequestMapping("/posts")
public class PostController {

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

    @PostMapping("/{id}/likes")
    @Operation(summary = "Like", description = "Like a post", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<PostResponse> like(@PathVariable("id") Long id,
                    @RequestBody LikeRequest request) {
        PostResponse like = postService.like(id, request);
        return ResponseEntity.ok(like);
    }

    @DeleteMapping("/{id}/likes")
    @Operation(summary = "Unlike", description = "Unlike a post", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<PostResponse> deleteLike(@PathVariable("id") Long postId,
                    @RequestParam("userId") Long userId) {
        PostResponse like = postService.deleteLike(postId, userId);
        return ResponseEntity.ok(like);
    }

    @PostMapping("/{id}/comments")
    @Operation(summary = "Comment", description = "Comment a comment", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<PostResponse> comment(@PathVariable("id") Long id,
                    @RequestBody CommentRequest request) {
        PostResponse comment = postService.comment(id, request);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/{id}/shares")
    @Operation(summary = "Share", description = "Share a comment", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public void share(@PathVariable("id") Long id,
                    @RequestBody PostShareRequest request) {
        postService.share(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get", description = "Get a user", tags = "User",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<PostResponse> get(@PathVariable("id") Long id) {
        PostResponse userResponse = postService.get(id);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}/comments/{commentId}")
    @Operation(summary = "Comment", description = "Comment a comment", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<PostResponse> deleteComment(@PathVariable("id") Long postId,
                    @PathVariable("commentId") Long commentId) {
        PostResponse comment = postService.deleteComment(postId, commentId);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/{id}/comments")
    @Operation(summary = "Comment", description = "Comment a comment", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<List<CommentResponse>> listComment(@PathVariable("id") Long postId) {
        List<CommentResponse> comment = postService.listComments(postId);
        return ResponseEntity.ok(comment);
    }
}
