package com.harera.socialnetwork.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.harera.socialnetwork.model.page.post.PagePostRequest;
import com.harera.socialnetwork.model.post.PostResponse;
import com.harera.socialnetwork.service.PageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Page", description = "Page API")
@RequestMapping("/pages")
public class PageController {

    @Autowired
    private PageService pageService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Create Page",
                    description = "Create a new page with the specified details.",
                    tags = { "Page" })
    @ApiResponses(value = { @ApiResponse(responseCode = "201",
                    description = "The page was created successfully.",
                    content = {
                            @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(
                                            implementation = PageResponse.class)) }),
            @ApiResponse(responseCode = "400",
                            description = "The request was malformed or invalid.",
                            content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                            implementation = ErrorResponse.class)) }) })
    public ResponseEntity<PageResponse> create(@RequestBody @Valid PageRequest request) {
        PageResponse pageResponse = pageService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pageResponse);
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Page", description = "Delete a page by id.",
                    tags = { "Page" })
    @ApiResponses(value = { @ApiResponse(responseCode = "204",
                    description = "The page has been deleted successfully.") })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        pageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/posts")
    @Operation(summary = "Create Post", description = "Create a post for page.",
                    tags = { "Page" })
    @ApiResponses(value = { @ApiResponse(responseCode = "200",
                    description = "The post was retrieved successfully.",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                                    implementation = PageResponse.class)) }),
            @ApiResponse(responseCode = "404",
                            description = "The post with the specified ID was not found.",
                            content = { @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                            implementation = ErrorResponse.class)) }) })
    public ResponseEntity<PostResponse> createPost(@PathVariable("id") Long id,
                    @RequestBody PagePostRequest request) {
        PostResponse post = pageService.createPost(id, request);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/{id}/posts")
    @Operation(summary = "List Posts", description = "List page posts.",
                    tags = { "Page" })
    @ApiResponses(value = { @ApiResponse(responseCode = "200",
                    description = "The post was retrieved successfully.",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                                    implementation = PageResponse.class)) }),
            @ApiResponse(responseCode = "404",
                            description = "The post with the specified ID was not found.",
                            content = { @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                            implementation = ErrorResponse.class)) }) })
    public ResponseEntity<List<PostResponse>> listPosts(@PathVariable("id") Long id) {
        List<PostResponse> posts = pageService.listPosts(id);
        return ResponseEntity.ok(posts);
    }
}
