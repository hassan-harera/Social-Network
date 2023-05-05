package com.harera.socialnetwork.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.harera.socialnetwork.model.post.Post;
import com.harera.socialnetwork.model.post.PostRequest;
import com.harera.socialnetwork.model.post.PostResponse;
import com.harera.socialnetwork.model.post.comment.Comment;
import com.harera.socialnetwork.model.post.comment.CommentRequest;
import com.harera.socialnetwork.model.post.comment.CommentResponse;
import com.harera.socialnetwork.model.post.react.React;
import com.harera.socialnetwork.model.post.react.ReactRequest;
import com.harera.socialnetwork.model.post.react.ReactResponse;
import com.harera.socialnetwork.model.post.share.PostShareRequest;
import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.repository.CommentRepository;
import com.harera.socialnetwork.repository.PostRepository;
import com.harera.socialnetwork.repository.ReactRepository;
import com.harera.socialnetwork.repository.UserRepository;
import com.harera.socialnetwork.util.ObjectMapperUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PostService {

    private final ReactRepository reactRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final Driver driver;

    public PostService(ReactRepository reactRepository, PostRepository postRepository,
                    UserRepository userRepository, ModelMapper modelMapper,
                    CommentRepository commentRepository, Driver driver) {
        this.reactRepository = reactRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.driver = driver;
    }

    public PostResponse create(PostRequest request) {
        Post post = modelMapper.map(request, Post.class);
        User user = userRepository.findById(request.getAuthorId()).orElseThrow();
        post.setAuthor(user);
        post = postRepository.save(post);
        return modelMapper.map(post, PostResponse.class);
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return toResponse(post);
    }

    private PostResponse toResponse(Post post) {
        long id = post.getIdentity();
        int commentsCount = postRepository.countComments(id);
        int reactsCount = postRepository.countReacts(id);
        int sharesCount = postRepository.countShares(id);

        post.setCommentsCount(commentsCount);
        post.setReactsCount(reactsCount);
        post.setSharesCount(sharesCount);

        return modelMapper.map(post, PostResponse.class);
    }

    public void delete(Long id) {
        List<String> queries = List.of(
                        "MATCH (p:Post)-[r1]->(a) WHERE id(p) = %d DELETE r1"
                                        .formatted(id),
                        "MATCH (b)-[r2]->(p:Post) WHERE id(p) = %d DELETE r2;"
                                        .formatted(id),
                        "MATCH (p:Post) WHERE id(p) = %d DELETE p;".formatted(id));
        Session session = driver.session();
        for (String query : queries) {
            session.run(query);
        }
    }

    public List<ReactResponse> listReacts(Long postId) {
        List<React> reacts = reactRepository.findAllByPostId(postId);
        return ObjectMapperUtils.mapAll(reacts, ReactResponse.class);
    }

    public void react(Long id, ReactRequest request) {
        Optional<React> optionalReact =
                        reactRepository.findByUserIdAndPostId(request.getAuthorId(), id);
        if (optionalReact.isPresent()) {
            return;
        }
        Post post = postRepository.react(request.getAuthorId(), id).orElseThrow();
        User user = userRepository.findById(request.getAuthorId()).orElseThrow();

        React react = new React();
        react.setPost(post);
        react.setUser(user);
        react.setType(request.getType());
        react.setDatetime(LocalDateTime.now());
        reactRepository.save(react);
    }

    public void deleteReact(Long postId, Long userId) {
        reactRepository.deleteReact(postId, userId);
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
        Post originalPost = postRepository.findById(id).orElseThrow();
        User user = userRepository.findById(request.getAuthorId()).orElseThrow();

        Post post = modelMapper.map(request, Post.class);
        post.setDatetime(LocalDateTime.now());
        post.setAuthor(user);
        post.setSharedPost(originalPost);

        postRepository.save(post);
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

    public List<PostResponse> list(int page) {
        page = Integer.max(page, 1) - 1;
        return postRepository.findAll(PageRequest.of(page, 10)).getContent().stream()
                        .map(this::toResponse).toList();
    }
}
