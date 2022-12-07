package com.orgofarmsgroup.controller.graphql;

import com.orgofarmsgroup.entity.UserEntity;
import com.orgofarmsgroup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserGraphQLController {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @QueryMapping
    Iterable<UserEntity> users(){
        log.info("UserGraphQLController.users()");
        return userRepository.findAll();
    }

    @QueryMapping
    Optional<UserEntity> userById(@Argument Long uid){
        log.info("UserGraphQLController.userById");
        return userRepository.findById(uid);
    }
}
