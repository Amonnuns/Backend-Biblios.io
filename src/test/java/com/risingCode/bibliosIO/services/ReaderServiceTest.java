package com.risingCode.bibliosIO.services;

import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.models.Reader;
import com.risingCode.bibliosIO.repository.ReaderRepository;
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

class ReaderServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private PasswordEncoder encoder;

    @Captor
    private ArgumentCaptor<Reader> readerArgumentCaptor;

    private ReaderService underTest;

    private Reader reader;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        underTest = new ReaderService(readerRepository, encoder);

        String username = "teste";
        String firstName = "MyTest";
        int age = 30;
        String cpf = "13244343208";
        String password = "testepass";
        String email = "mytestmail@email.com";

        this.reader = new Reader(username,firstName,null,
                age,password,
                cpf, email,true);

    }

    @Test
    void registerUserWhoDoesntExist() {

        Example<Reader> userExample = Example.of(reader);

        //given
        given(readerRepository
                .findByUsername(reader.getUserName())
        ).willReturn(Optional.empty());

        //when
        underTest.registerReader(reader);

        //then
        then(readerRepository).should()
                .save(readerArgumentCaptor.capture());

        Reader readerArgumentCaptorValue = readerArgumentCaptor.getValue();

        assertThat(readerArgumentCaptorValue).isEqualTo(reader);


    }


    @Test
    void authenticateReader() {

        String username = "teste";
        String password = "testepass";

        UserLoginFormDto userLoginForm = new UserLoginFormDto(username, password);

        //given
        given(readerRepository.findByUsername(reader.getUserName()))
                .willReturn(Optional.of(reader));
        given(encoder.matches(password, reader.getPassword())).willReturn(true);


        assertThat(underTest.authenticateReader(userLoginForm))
                .isEqualTo(ResponseEntity.status(HttpStatus.ACCEPTED).body(true));
    }
}