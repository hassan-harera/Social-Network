package com.harera.socialnetwork.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.harera.socialnetwork.model.group.Group;
import com.harera.socialnetwork.model.group.GroupRequest;
import com.harera.socialnetwork.model.group.GroupResponse;
import com.harera.socialnetwork.model.group.follow.GroupFollow;
import com.harera.socialnetwork.model.group.follow.GroupFollowRequest;
import com.harera.socialnetwork.model.group.join.GroupJoin;
import com.harera.socialnetwork.model.group.join.GroupJoinRequest;
import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.repository.GroupRepository;
import com.harera.socialnetwork.repository.PostRepository;
import com.harera.socialnetwork.repository.UserRepository;

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

        group.getFollowers().removeIf(groupFollow -> groupFollow.getUser().getId()
                        .equals(user.getId()));
        groupRepository.save(group);
    }
}
