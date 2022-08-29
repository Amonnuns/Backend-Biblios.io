package com.risingCode.bibliosIO.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.risingCode.bibliosIO.dto.UserDTO;
import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.repository.ReaderRepository;
import com.risingCode.bibliosIO.services.ReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReaderController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class ReaderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReaderService readerService;


    @Test
    void registerUser() throws Exception {
        String username = "teste";
        String firstName = "MyTest";
        int age = 30;
        String cpf = "13244343208";
        String password = "testepass";

        UserDTO user = new UserDTO(username,firstName,
                null,age,password,cpf);

        String body = objectMapper.writeValueAsString(user);

        mvc.perform(post("/api/v1/user/subscribe")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    void authenticateUser() throws Exception {
        String username = "test";
        String password = "testepass";

        UserLoginFormDto user = new UserLoginFormDto(username, password);

        String body = objectMapper.writeValueAsString(user);

        when(readerService.authenticateReader(any(UserLoginFormDto.class)))
                .thenReturn(true);


        mvc.perform(post("/api/v1/user/auth")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isAccepted());
    }
}