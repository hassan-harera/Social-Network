package com.harera.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harera.socialnetwork.model.page.PageRequest;
import com.harera.socialnetwork.model.page.PageResponse;
import com.harera.socialnetwork.model.page.follow.PageFollowRequest;
import com.harera.socialnetwork.model.page.like.PageLikeRequest;
import com.harera.socialnetwork.service.PageService;
import com.harera.socialnetwork.service.PostService;
import com.harera.socialnetwork.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Page", description = "Page API")
@RequestMapping("/pages")
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private PageService pageService;

    @PostMapping
    @Operation(summary = "Create Page", description = "Create a page", tags = "Page",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<PageResponse> create(@RequestBody PageRequest request) {
        PageResponse pageResponse = pageService.create(request);
        return ResponseEntity.ok(pageResponse);
    }

    @PostMapping("/{id}/followers")
    @Operation(summary = "Follow", description = "Follow a page by id", tags = "Page",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public void comment(@PathVariable("id") Long id,
                    @RequestBody PageFollowRequest request) {
        pageService.follow(id, request);
    }

    @PostMapping("/{id}/likes")
    @Operation(summary = "Like", description = "Like a page by id", tags = "Page",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public void like(@PathVariable("id") Long id, @RequestBody PageLikeRequest request) {
        pageService.like(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get", description = "Get page by id", tags = "Page",
                    responses = @ApiResponse(responseCode = "200",
                                    description = "success|Ok"))
    public ResponseEntity<PageResponse> get(@PathVariable("id") Long id) {
        PageResponse pageResponse = pageService.get(id);
        return ResponseEntity.ok(pageResponse);
    }
}
