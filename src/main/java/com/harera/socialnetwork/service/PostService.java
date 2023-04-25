package com.harera.socialnetwork.service;

import java.time.LocalDateTime;
import java.util.Optional;

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
import com.harera.socialnetwork.model.post.share.PostShare;
import com.harera.socialnetwork.model.post.share.PostShareRequest;
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

    public PostResponse like(Long id, LikeRequest request) {
        Post like = postRepository.like(request.getAuthorId(), id).orElseThrow();
        return modelMapper.map(like, PostResponse.class);
    }

    public PostResponse unlike(Long postId, Long userId) {
        Post like = postRepository.unlike(userId, postId).orElseThrow();
        return modelMapper.map(like, PostResponse.class);
    }

    public void comment(Long id, CommentRequest request) {
        Comment comment = modelMapper.map(request, Comment.class);
        Post post = postRepository.findById(id).orElseThrow();
        User user = userRepository.findById(request.getAuthorId()).orElseThrow();
        comment.setAuthor(user);
        post.getComments().add(comment);
        postRepository.save(post);
    }

    public void share(Long id, PostShareRequest request) {
        PostShare share = modelMapper.map(request, PostShare.class);

        User user = userRepository.findById(request.getAuthorId()).orElseThrow();
        share.setAuthor(user);

        Post post = postRepository.findById(id).orElseThrow();
        post.getShares().add(share);

        postRepository.save(post);
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return modelMapper.map(post, PostResponse.class);
    }
}
