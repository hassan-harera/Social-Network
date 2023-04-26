package com.harera.socialnetwork.service;

import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harera.socialnetwork.model.post.Post;
import com.harera.socialnetwork.model.post.PostRequest;
import com.harera.socialnetwork.model.post.PostResponse;
import com.harera.socialnetwork.model.post.comment.Comment;
import com.harera.socialnetwork.model.post.comment.CommentRequest;
import com.harera.socialnetwork.model.post.comment.CommentResponse;
import com.harera.socialnetwork.model.post.like.LikeRequest;
import com.harera.socialnetwork.model.post.share.PostShare;
import com.harera.socialnetwork.model.post.share.PostShareRequest;
import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.repository.CommentRepository;
import com.harera.socialnetwork.repository.PostRepository;
import com.harera.socialnetwork.repository.UserRepository;

@Log4j2
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommentRepository commentRepository;

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

    public PostResponse deleteLike(Long postId, Long userId) {
        Post like = postRepository.deleteLike(userId, postId).orElseThrow();
        return modelMapper.map(like, PostResponse.class);
    }

    public PostResponse comment(Long postId, CommentRequest request) {
        Comment comment = modelMapper.map(request, Comment.class);

        User user = userRepository.findById(request.getAuthorId()).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();

        comment.setUser(user);
        comment.setPost(post);
        comment.setDatetime(LocalDateTime.now());
        commentRepository.save(comment);
        return modelMapper.map(post, PostResponse.class);
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

    public void deleteComment(Long postId, Long commentId) {
        commentRepository.deleteComment(postId, commentId);
    }

    public List<CommentResponse> listComments(Long postId) {
        List<Comment> comments = commentRepository.listComments(postId);
        List<CommentResponse> commentResponses = new LinkedList<>();
        for (Comment comment : comments) {
            log.info(comment.toString());
            CommentResponse commentResponse =
                            modelMapper.map(comment, CommentResponse.class);
            commentResponses.add(commentResponse);
        }
        return commentResponses;
    }
}
