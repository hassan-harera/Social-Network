package com.harera.socialnetwork.service;

import static com.harera.socialnetwork.util.ObjectMapperUtils.mapAll;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.model.user.UserRequest;
import com.harera.socialnetwork.model.user.UserResponse;
import com.harera.socialnetwork.model.user.follow.UserFollow;
import com.harera.socialnetwork.model.user.follow.UserFollowRequest;
import com.harera.socialnetwork.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

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

    public void follow(Long followingId, UserFollowRequest request) {
        User following = userRepository.findById(followingId).orElseThrow();
        User follower = userRepository.findById(request.getFollowerId()).orElseThrow();

        UserFollow userFollow = new UserFollow();
        userFollow.setUser(follower);
        userFollow.setDateTime(LocalDateTime.now());

        following.getFollowers().add(userFollow);
        userRepository.save(following);
    }

    public UserResponse get(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return mapToUserResponse(user);
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse response = modelMapper.map(user, UserResponse.class);
        return response;
    }

    public List<UserResponse> listFollowers(Long id) {
        List<User> followers = userRepository.findUserFollowersById(id);
        return mapAll(followers, UserResponse.class);
    }

    public List<UserResponse> listFollowings(Long id) {
        List<User> followings = userRepository.findUserFollowingsById(id);
        return mapAll(followings, UserResponse.class);
    }
}
