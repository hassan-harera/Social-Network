package com.harera.socialnetwork.controller;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harera.socialnetwork.model.group.GroupRequest;
import com.harera.socialnetwork.model.group.GroupResponse;
import com.harera.socialnetwork.model.group.follow.GroupFollowRequest;
import com.harera.socialnetwork.model.group.join.GroupJoinRequest;
import com.harera.socialnetwork.model.post.PostRequest;
import com.harera.socialnetwork.model.post.PostResponse;
import com.harera.socialnetwork.model.user.UserResponse;
import com.harera.socialnetwork.service.GroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@Tag(name = "Group", description = "Group API")
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    @Operation(summary = "Create Group", description = "Create a group", tags = "Group",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<GroupResponse> create(@RequestBody GroupRequest request) {
        GroupResponse groupResponse = groupService.create(request);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(groupResponse);
    }

    @DeleteMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete Group", description = "Delete a group by id",
                    tags = "Group", responses = @ApiResponse(responseCode = "204",
                                    description = "Success|No Content"))
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/follow")
    @Operation(summary = "Follow Group", description = "Follow a group", tags = "Group",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public void follow(@PathVariable("id") Long id,
                    @RequestBody GroupFollowRequest request) {
        groupService.follow(id, request);
    }

    @PostMapping("/{id}/unfollow")
    @Operation(summary = "Unfollow Group", description = "Unfollow a group",
                    tags = "Group", responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public void unfollow(@PathVariable("id") Long id,
                    @RequestBody GroupFollowRequest request) {
        groupService.unfollow(id, request);
    }

    @PostMapping("/{id}/join")
    @Operation(summary = "Join Group", description = "Join a group", tags = "Group",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public void join(@PathVariable("id") Long id, @RequestBody GroupJoinRequest request) {
        groupService.join(id, request);
    }

    @PostMapping("/{id}/leave")
    @Operation(summary = "Leave Group", description = "Leave a group", tags = "Group",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public void leave(@PathVariable("id") Long id,
                    @RequestBody GroupJoinRequest request) {
        groupService.leave(id, request);
    }

    @GetMapping("/{id}/users")
    @Operation(summary = "Get group users", description = "Get a group users",
                    tags = "Group", responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<List<UserResponse>> getUsers(@PathVariable("id") Long id) {
        List<UserResponse> users = groupService.listUsers(id);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{id}/posts")
    @Operation(summary = "Create post",
                    description = "Create a post in a group with group id",
                    tags = "Group", responses = @ApiResponse(responseCode = "201",
                                    description = "Success|Created"))
    public ResponseEntity<PostResponse> createPost(@PathVariable("id") Long id,
                    @RequestBody PostRequest postRequest) {
        PostResponse postResponse = groupService.createPost(id, postRequest);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/{id}/posts")
    @Operation(summary = "List group posts",
                    description = "List group posts with group id", tags = "Group",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "Success|Ok"))
    public ResponseEntity<List<PostResponse>> listPosts(@PathVariable("id") Long id) {
        List<PostResponse> posts = groupService.listPosts(id);
        return ResponseEntity.ok(posts);
    }
}
