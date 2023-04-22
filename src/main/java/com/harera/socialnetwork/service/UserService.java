package com.harera.socialnetwork.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.harera.socialnetwork.model.user.User;
import com.harera.socialnetwork.model.user.UserRequest;
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

    public void create(UserRequest request) {
        User user = modelMapper.map(request, User.class);
        userRepository.save(user);
        //        .doOnSuccess(
        //                user1 -> log.info("User created successfully")
        //        ).doOnError(
        //                throwable -> log.error("Error creating user")
        //        ).subscribe(
        //                user1 -> log.info("User created successfully")
        //        );
    }
}
