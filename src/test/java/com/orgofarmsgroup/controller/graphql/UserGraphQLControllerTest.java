package com.orgofarmsgroup.controller.graphql;

import com.orgofarmsgroup.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
@AutoConfigureGraphQlTester
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

        graphQlTester.document(document)
                .execute()
                .path("users")
                .entityList(UserEntity.class);
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

        graphQlTester.document(document)
                .execute()
                .path("userById")
                .entity(UserEntity.class);
    }
}