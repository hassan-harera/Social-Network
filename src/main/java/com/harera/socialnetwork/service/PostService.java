package com.harera.socialnetwork.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harera.socialnetwork.model.post.comment.Comment;
import com.harera.socialnetwork.model.post.comment.CommentRequest;
import com.harera.socialnetwork.model.post.like.Like;
import com.harera.socialnetwork.model.post.like.LikeRequest;
import com.harera.socialnetwork.model.post.Post;
import com.harera.socialnetwork.model.post.PostRequest;
import com.harera.socialnetwork.model.post.PostResponse;
import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.repository.PostRepository;
import com.harera.socialnetwork.repository.UserRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PostResponse create(PostRequest request) {
        Post post = modelMapper.map(request, Post.class);
        User user = userRepository.findById(request.getAuthorId()).orElseThrow();
        post.setAuthor(user);
        post = postRepository.save(post);
        return modelMapper.map(post, PostResponse.class);
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
}
