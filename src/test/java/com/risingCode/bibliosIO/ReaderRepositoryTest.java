package com.risingCode.bibliosIO;

import com.risingCode.bibliosIO.models.Reader;
import com.risingCode.bibliosIO.repository.ReaderRepository;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ReaderRepositoryTest {

    @Autowired
    private ReaderRepository readerRepository;

    @AfterEach
    void tearDown(){
        readerRepository.deleteAll();
    }

    @Test
    void mustFindUserByUserName(){

        String userName = "userTest";
        String firstName = "reader";
        String password = "passwordTest";
        String cpf = "XXXXXXXXXXX";
        String email = "mytestmail@email.com";
        Boolean enabled = true;

        Reader reader = new Reader(userName, firstName,
                null,0,
                password, cpf,
                email, enabled);

        readerRepository.save(reader);

        Optional<Reader> optionalUser = readerRepository
                .findByUsername(userName);

        assertThat(optionalUser)
                .isPresent()
                .hasValueSatisfying(u -> assertThat(u)
                        .isEqualTo(reader));

    }

    @Test
    void mustFindUserByUserNameAndPassword(){

        String userName = "userTest";
        String firstName = "reader";
        String password = "passwordTest";
        String cpf = "XXXXXXXXXXX";
        String email = "mytestmail@email.com";
        Boolean enabled = true;

        Reader reader = new Reader(userName, firstName,
                null,0,
                password, cpf,
                email, enabled);

        readerRepository.save(reader);

        Optional<Reader> optionalUser = readerRepository
                .findByUsernameAndPassword(userName, password);

        assertThat(optionalUser)
                .isPresent()
                .hasValueSatisfying(u -> assertThat(u)
                        .isEqualTo(reader));

    }


}
