package com.harera.socialnetwork.controller;

import org.springframework.http.ResponseEntity;
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
import com.harera.socialnetwork.service.GroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

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
        return ResponseEntity.ok(groupResponse);
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

    @GetMapping("/{id}")
    @Operation(summary = "Get Group", description = "Get a group by id", tags = "Group",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<GroupResponse> get(@PathVariable("id") Long id) {
        GroupResponse groupResponse = groupService.get(id);
        return ResponseEntity.ok(groupResponse);
    }
}
