package com.harera.socialnetwork.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.harera.socialnetwork.model.page.Page;
import com.harera.socialnetwork.model.page.PageRequest;
import com.harera.socialnetwork.model.page.PageResponse;
import com.harera.socialnetwork.model.page.follow.PageFollowRequest;
import com.harera.socialnetwork.model.page.like.PageLikeRequest;
import com.harera.socialnetwork.model.page.post.PagePostRequest;
import com.harera.socialnetwork.model.post.Post;
import com.harera.socialnetwork.model.post.PostResponse;
import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.repository.PageRepository;
import com.harera.socialnetwork.repository.PostRepository;
import com.harera.socialnetwork.repository.UserRepository;
import com.harera.socialnetwork.util.ObjectMapperUtils;

@Service
public class PageService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PageRepository pageRepository;

    public PageService(PostRepository postRepository, UserRepository userRepository,
                    ModelMapper modelMapper, PageRepository pageRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.pageRepository = pageRepository;
    }

    public PageResponse create(PageRequest request) {
        Page page = modelMapper.map(request, Page.class);
        page.setDatetime(LocalDateTime.now());

        User user = userRepository.findById(request.getOwnerId()).orElseThrow();
        page.setOwner(user);
        page.setCreator(user);

        page = pageRepository.save(page);

        pageRepository.follow(page.getIdentity(), request.getOwnerId());
        pageRepository.like(page.getIdentity(), request.getOwnerId());

        return modelMapper.map(page, PageResponse.class);
    }

    public PageResponse get(Long id) {
        Page page = pageRepository.findById(id).orElseThrow();
        return modelMapper.map(page, PageResponse.class);
    }

    public void follow(Long id, PageFollowRequest request) {
        pageRepository.follow(id, request.getUserId());
    }

    public void like(Long id, PageLikeRequest request) {
        pageRepository.follow(id, request.getUserId());
        pageRepository.like(id, request.getUserId());
    }

    public PostResponse createPost(Long id, PagePostRequest request) {
        Page page = pageRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Page not found"));

        Post post = modelMapper.map(request, Post.class);
        post.setDatetime(LocalDateTime.now());
        post.setPage(page);
        postRepository.save(post);

        pageRepository.post(id, post.getIdentity());
        return modelMapper.map(post, PostResponse.class);
    }

    public List<PostResponse> listPosts(Long id) {
        List<Long> ids = pageRepository.listPostIds(id);
        List<Post> posts = postRepository.findAllById(ids);
        return ObjectMapperUtils.mapAll(posts, PostResponse.class);
    }

    public void delete(Long id) {
        pageRepository.deleteRelations(id);
        pageRepository.deleteById(id);
    }
}
