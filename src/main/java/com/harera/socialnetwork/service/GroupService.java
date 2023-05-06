package com.harera.socialnetwork.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.harera.socialnetwork.model.group.Group;
import com.harera.socialnetwork.model.group.GroupRequest;
import com.harera.socialnetwork.model.group.GroupResponse;
import com.harera.socialnetwork.model.group.follow.GroupFollow;
import com.harera.socialnetwork.model.group.follow.GroupFollowRequest;
import com.harera.socialnetwork.model.group.join.GroupJoin;
import com.harera.socialnetwork.model.group.join.GroupJoinRequest;
import com.harera.socialnetwork.model.post.Post;
import com.harera.socialnetwork.model.post.PostRequest;
import com.harera.socialnetwork.model.post.PostResponse;
import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.model.user.UserResponse;
import com.harera.socialnetwork.repository.GroupRepository;
import com.harera.socialnetwork.repository.PostRepository;
import com.harera.socialnetwork.repository.UserRepository;
import com.harera.socialnetwork.util.ObjectMapperUtils;

@Service
public class GroupService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final GroupRepository groupRepository;

    public GroupService(PostRepository postRepository, UserRepository userRepository,
                    ModelMapper modelMapper, GroupRepository groupRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.groupRepository = groupRepository;
    }

    public GroupResponse create(GroupRequest request) {
        Group group = modelMapper.map(request, Group.class);
        group.setCreatedAt(LocalDateTime.now());

        User user = userRepository.findById(request.getOwnerId()).orElseThrow();
        group.setOwner(user);
        group.setCreator(user);

        group = groupRepository.save(group);
        groupRepository.join(request.getOwnerId(), group.getIdentity());
        groupRepository.follow(request.getOwnerId(), group.getIdentity());
        return modelMapper.map(group, GroupResponse.class);
    }

    public GroupResponse get(Long id) {
        Group group = groupRepository.findById(id).orElseThrow();
        return modelMapper.map(group, GroupResponse.class);
    }

    public void follow(Long id, GroupFollowRequest request) {
        Group group = groupRepository.findById(id).orElseThrow();
        User user = userRepository.findById(request.getUserId()).orElseThrow();

        GroupFollow groupFollow = new GroupFollow();
        groupFollow.setUser(user);
        groupFollow.setDateTime(LocalDateTime.now());

        group.getFollowers().add(groupFollow);
        groupRepository.save(group);
    }

    public void join(Long id, GroupJoinRequest request) {
        Group group = groupRepository.findById(id).orElseThrow();
        User user = userRepository.findById(request.getUserId()).orElseThrow();

        GroupJoin groupJoin = new GroupJoin();
        groupJoin.setUser(user);
        groupJoin.setDateTime(LocalDateTime.now());

        group.getJoined().add(groupJoin);
        groupRepository.save(group);
    }

    public void leave(Long id, GroupJoinRequest request) {
        groupRepository.leave(request.getUserId(), id);
    }

    public void unfollow(Long id, GroupFollowRequest request) {
        Group group = groupRepository.findById(id).orElseThrow();
        User user = userRepository.findById(request.getUserId()).orElseThrow();

        group.getFollowers().removeIf(groupFollow -> groupFollow.getUser().getIdentity()
                        .equals(user.getIdentity()));
        groupRepository.save(group);
    }

    public List<UserResponse> listUsers(Long id) {
        List<Long> userIds = groupRepository.findGroupUserIds(id);
        return ObjectMapperUtils.mapAll(userRepository.findAllById(userIds),
                        UserResponse.class);
    }

    public List<PostResponse> listPosts(Long id) {
        List<Long> postIds = groupRepository.findGroupPostIds(id);
        return ObjectMapperUtils.mapAll(postRepository.findAllById(postIds),
                        PostResponse.class);
    }

    public PostResponse createPost(Long id, PostRequest postRequest) {
        Post post = modelMapper.map(postRequest, Post.class);
        User user = userRepository.findById(postRequest.getAuthorId()).orElseThrow();
        post.setAuthor(user);
        post = postRepository.save(post);
        groupRepository.addPost(id, post.getIdentity());
        return modelMapper.map(post, PostResponse.class);
    }

    public void delete(Long id) {
        groupRepository.deleteGroupRelationsById(id);
        groupRepository.deleteById(id);
    }
}
