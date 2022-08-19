package com.risingCode.bibliosIO.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.risingCode.bibliosIO.dto.UserDto;
import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.models.User;
import com.risingCode.bibliosIO.repository.UserRepository;
import com.risingCode.bibliosIO.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void registerUser() throws Exception {
        String username = "teste";
        String firstName = "MyTest";
        int age = 30;
        String cpf = "13244343208";
        String password = "testepass";

        UserDto user = new UserDto(username,firstName,
                null,age,password,cpf);

        String body = objectMapper.writeValueAsString(user);

        mvc.perform(post("/api/v1/user/subscribe")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    void authenticateUser() throws Exception {
        //todo
    }
}