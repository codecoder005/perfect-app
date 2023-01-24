package com.orgofarmsgroup.service;

import com.orgofarmsgroup.entity.UserEntity;
import com.orgofarmsgroup.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGetUsers() {
        List<UserEntity> mockedUsers = new ArrayList<>();
        mockedUsers.add(new UserEntity(101L, "John", "john@email.com"));
        when(userRepository.findAll()).thenReturn(mockedUsers);

        List<UserEntity> serviceResponse = userService.getUsers();
        assertEquals(1, serviceResponse.size());
    }
}