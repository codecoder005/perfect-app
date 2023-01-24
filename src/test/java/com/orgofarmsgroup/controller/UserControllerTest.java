package com.orgofarmsgroup.controller;

import com.orgofarmsgroup.entity.UserEntity;
import com.orgofarmsgroup.service.UserService;
import com.orgofarmsgroup.util.AppConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("unit-test")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName(value = "test users root api")
    void testUsersRootAPI200() throws Exception {
        List<UserEntity> mockedUsersFromService = new ArrayList<>();
        mockedUsersFromService.add(new UserEntity(101L, "John", "john@email.com"));
        when(userService.getUsers()).thenReturn(mockedUsersFromService);

        mockMvc.perform(get(AppConstants.ApiURI.UsersAPI.USERS_ROOT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].uid").value("101"))
                .andExpect(jsonPath("$.data[0].name").value("John"))
                .andExpect(jsonPath("$.data[0].email").value("john@email.com"))
                .andExpect(jsonPath("$.requestObject.requestURL").value("http://localhost/users"))
                .andExpect(jsonPath("$.requestObject.requestMethod").value("GET"));
    }

    @Test
    @DisplayName(value = "root API Service Unavailable")
    void testUsersRootAPIServiceUnavailable() throws Exception {
        when(userService.getUsers()).thenThrow(new RuntimeException("Intentionally thrown to check service unavailability"));
        mockMvc.perform(get(AppConstants.ApiURI.UsersAPI.USERS_ROOT))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.statusCode").value("503"))
                .andExpect(jsonPath("$.data").value("Something went wrong."));
    }
}