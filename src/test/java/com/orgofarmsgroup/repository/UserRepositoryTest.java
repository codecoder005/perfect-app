package com.orgofarmsgroup.repository;

import com.orgofarmsgroup.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Slf4j
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private static UserEntity user;

    @BeforeAll
    static void initialSetup() {
        user = new UserEntity(101L, "John", "john@email.com");
    }

    @Test
    @DisplayName(value = "find by id")
    void testFindById() {
        log.info("test: testFindById succeeded.");
        userRepository.save(user);

        Optional<UserEntity> response = userRepository.findById(user.getUid());

        assertTrue(response.isPresent());
        assertEquals(user.getUid(), response.get().getUid());
        assertEquals(user.getName(), response.get().getName());
        assertEquals(user.getEmail(), response.get().getEmail());
    }

    @Test
    @DisplayName(value = "find email by uid")
    void testFindEmailByUid() {
        String email = userRepository.findEmailByUid(user.getUid());
        assertEquals(user.getEmail(), email);
    }

    @Test
    @DisplayName(value = "find uid by email")
    void testFindUidByEmail() {
        Long uid = userRepository.findUidByEmail(user.getEmail());
        assertEquals(user.getUid(), uid);
    }

    @Test
    @DisplayName(value = "find name by uid")
    void testFindNameByUid() {
        String name = userRepository.findNameByUid(user.getUid());
        assertEquals(user.getName(), name);
    }

    @Test
    @DisplayName(value = "find name by email")
    void testFindNameByEmail() {
        String name = userRepository.findNameByEmail(user.getEmail());
        assertEquals(user.getName(), name);
    }

    @Test
    @DisplayName(value = "find by email")
    void testFindByEmail() {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(user.getEmail());
        assertTrue(optionalUser.isPresent());

        UserEntity responseUser = optionalUser.get();
        assertEquals(user.getUid(), responseUser.getUid());
        assertEquals(user.getName(), responseUser.getName());
        assertEquals(user.getEmail(), responseUser.getEmail());
    }
}