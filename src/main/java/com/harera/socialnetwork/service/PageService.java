package com.harera.socialnetwork.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.harera.socialnetwork.model.page.follow.PageFollow;
import com.harera.socialnetwork.model.page.like.PageLike;
import com.harera.socialnetwork.model.post.comment.Comment;
import com.harera.socialnetwork.model.post.comment.CommentRequest;
import com.harera.socialnetwork.model.post.like.Like;
import com.harera.socialnetwork.model.post.like.LikeRequest;
import com.harera.socialnetwork.model.page.Page;
import com.harera.socialnetwork.model.page.PageRequest;
import com.harera.socialnetwork.model.page.PageResponse;
import com.harera.socialnetwork.model.page.follow.PageFollowRequest;
import com.harera.socialnetwork.model.page.like.PageLikeRequest;
import com.harera.socialnetwork.model.post.Post;
import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.repository.PageRepository;
import com.harera.socialnetwork.repository.PostRepository;
import com.harera.socialnetwork.repository.UserRepository;

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

        page = pageRepository.save(page);
        return modelMapper.map(page, PageResponse.class);
    }

    public void comment(Long id, CommentRequest request) {
        Comment comment = modelMapper.map(request, Comment.class);
        Post post = postRepository.findById(id).orElseThrow();
        User user = userRepository.findById(request.getAuthorId()).orElseThrow();
        comment.setAuthor(user);
        post.getComments().add(comment);
        postRepository.save(post);
    }

    public void like(Long id, LikeRequest request) {
        Post post = postRepository.findById(id).orElseThrow();
        User user = userRepository.findById(request.getAuthorId()).orElseThrow();

        Like like = new Like();
        like.setAuthor(user);
        like.setDatetime(LocalDateTime.now());

        post.getLikes().add(like);
        postRepository.save(post);
    }

    public PageResponse get(Long id) {
        Page page = pageRepository.findById(id).orElseThrow();
        return modelMapper.map(page, PageResponse.class);
    }

    public void follow(Long id, PageFollowRequest request) {
        Page page = pageRepository.findById(id).orElseThrow();
        User user = userRepository.findById(request.getUserId()).orElseThrow();

        PageFollow pageFollow = new PageFollow();
        pageFollow.setUser(user);
        pageFollow.setDateTime(LocalDateTime.now());

        page.getFollowers().add(pageFollow);
        pageRepository.save(page);
    }

    public void like(Long id, PageLikeRequest request) {
        Page page = pageRepository.findById(id).orElseThrow();

        User user = userRepository.findById(request.getUserId()).orElseThrow();
        PageLike pageLike = new PageLike();
        pageLike.setUser(user);
        pageLike.setDateTime(LocalDateTime.now());

        page.getLikes().add(pageLike);

        pageRepository.save(page);
    }
}
