package com.orgofarmsgroup.repository;

import com.orgofarmsgroup.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindById() {
        log.info("test: testFindById succeeded.");
        UserEntity user = new UserEntity(101L, "John", "john@email.com");
        userRepository.save(user);

        Optional<UserEntity> response = userRepository.findById(user.getUid());

        assertTrue(response.isPresent());
        assertEquals(user.getUid(), response.get().getUid());
        assertEquals(user.getName(), response.get().getName());
        assertEquals(user.getEmail(), response.get().getEmail());
    }
}