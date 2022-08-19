package com.risingCode.bibliosIO.services;

import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.models.User;
import com.risingCode.bibliosIO.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    private UserService underTest;

    private User user;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepository, encoder);

        String username = "teste";
        String firstName = "MyTest";
        int age = 30;
        String cpf = "13244343208";
        String password = "testepass";

        this.user = new User(username,firstName,null,
                age,password,cpf,true);

    }

    @Test
    void registerUserWhoDoesntExist() {

        Example<User> userExample = Example.of(user);

        //given
        given(userRepository
                .findByUsername(user.getUserName())
        ).willReturn(Optional.empty());

        //when
        underTest.registerUser(user);

        //then
        then(userRepository).should()
                .save(userArgumentCaptor.capture());

        User userArgumentCaptorValue = userArgumentCaptor.getValue();

        assertThat(userArgumentCaptorValue).isEqualTo(user);


    }


    @Test
    void authenticateUser() {

        String username = "teste";
        String password = "testepass";

        UserLoginFormDto userLoginForm = new UserLoginFormDto(username, password);

        //given
        given(userRepository.findByUsername(user.getUserName()))
                .willReturn(Optional.of(user));
        given(encoder.matches(password,user.getPassword())).willReturn(true);


        assertThat(underTest.authenticateUser(userLoginForm))
                .isEqualTo(ResponseEntity.status(HttpStatus.ACCEPTED).body(true));
    }
}