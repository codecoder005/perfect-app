package com.orgofarmsgroup.controller.graphql;

import com.orgofarmsgroup.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureGraphQlTester
@Slf4j
class UserGraphQLControllerTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    @DisplayName(value = "graphql controller: users")
    void testUsers() {
        // language=GraphQL
        String document = """
            query {
                users {
                    uid
                    name
                    email
                }
            }
        """;

        GraphQlTester.EntityList<UserEntity> response = graphQlTester.document(document)
                                                                    .execute()
                                                                    .path("users")
                                                                    .entityList(UserEntity.class);
        UserEntity user = response.get().get(0);
        assertEquals(101L, user.getUid());
        assertEquals("John", user.getName());
        assertEquals("john@email.com", user.getEmail());
    }

    @Test
    @DisplayName(value = "graphql controller: userById")
    void testUserById() {
        // language=GraphQL
        String document = """
            query {
                userById(uid: 101) {
                    uid
                    name
                    email
                }
            }
        """;

        GraphQlTester.Entity<UserEntity, ?> responseUser = graphQlTester.document(document)
                .execute()
                .path("userById")
                .entity(UserEntity.class);

        UserEntity user = responseUser.get();
        assertEquals(101L, user.getUid());
        assertEquals("John", user.getName());
        assertEquals("john@email.com", user.getEmail());
    }
}