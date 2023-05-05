package com.harera.socialnetwork.controller;

import java.util.List;

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
import com.harera.socialnetwork.model.post.react.ReactRequest;
import com.harera.socialnetwork.model.post.react.ReactResponse;
import com.harera.socialnetwork.model.post.share.PostShareRequest;
import com.harera.socialnetwork.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

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

    @PostMapping("/{id}/reacts")
    @Operation(summary = "React", description = "Make a raact to a post", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<Void> react(@PathVariable("id") Long id,
                    @RequestBody ReactRequest request) {
        postService.react(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/reacts")
    @Operation(summary = "List Reacts", description = "List post reacts", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<List<ReactResponse>> listReacts(
                    @PathVariable("id") Long postId) {
        List<ReactResponse> reactResponses = postService.listReacts(postId);
        return ResponseEntity.ok(reactResponses);
    }

    @DeleteMapping("/{id}/reacts")
    @Operation(summary = "Unreact", description = "Unreact a post", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<PostResponse> deleteReact(@PathVariable("id") Long postId,
                    @RequestParam("userId") Long userId) {
        postService.deleteReact(postId, userId);
        return ResponseEntity.ok().build();
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
    @Operation(summary = "Get", description = "Get a user", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<PostResponse> get(@PathVariable("id") Long id) {
        PostResponse userResponse = postService.get(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    @Operation(summary = "List", description = "List posts", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<List<PostResponse>> list(
                    @RequestParam(value = "page", defaultValue = "1") int page) {
        List<PostResponse> userResponse = postService.list(page);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete", description = "Delete a user", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<PostResponse> delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/comments/{commentId}")
    @Operation(summary = "Comment", description = "Comment a comment", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public void deleteComment(@PathVariable("id") Long postId,
                    @PathVariable("commentId") Long commentId) {
        postService.deleteComment(postId, commentId);
    }

    @GetMapping("/{id}/comments")
    @Operation(summary = "Comment", description = "Comment a comment", tags = "Post",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<List<CommentResponse>> listComment(
                    @PathVariable("id") Long postId) {
        List<CommentResponse> comment = postService.listComments(postId);
        return ResponseEntity.ok(comment);
    }
}
