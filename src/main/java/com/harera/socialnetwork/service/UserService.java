package com.harera.socialnetwork.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.harera.socialnetwork.model.follow.FollowingRequest;
import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.model.user.UserRequest;
import com.harera.socialnetwork.model.user.UserResponse;
import com.harera.socialnetwork.repository.UserRepository;
import com.harera.socialnetwork.util.ObjectMapperUtils;

import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.harera.socialnetwork.util.ObjectMapperUtils.mapAll;

@Log4j2
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserResponse create(UserRequest request) {
        User user = modelMapper.map(request, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    public void follow(Long followingId, FollowingRequest request) {
        User following = userRepository.findById(followingId).orElseThrow();
        User follower = userRepository.findById(request.getFollowerId()).orElseThrow();

        following.getFollowers().add(follower);
        userRepository.save(following);
    }

    public UserResponse get(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return mapToUserResponse(user);
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse response = modelMapper.map(user, UserResponse.class);
        response.setFollowerList(mapAll(user.getFollowers(), UserResponse.class));
        response.setFollowingList(mapAll(user.getFollowings(), UserResponse.class));
        return response;
    }
}
