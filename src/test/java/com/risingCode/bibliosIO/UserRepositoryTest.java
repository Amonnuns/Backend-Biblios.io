package com.risingCode.bibliosIO;

import com.risingCode.bibliosIO.models.User;
import com.risingCode.bibliosIO.repository.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void mustFindUserByUserName(){

        String userName = "userTest";
        String firstName = "user";
        String password = "passwordTest";
        String cpf = "XXXXXXXXXXX";
        Boolean enabled = true;

        User user = new User(userName, firstName,
                null,0,
                password, cpf, enabled);

        userRepository.save(user);

        Optional<User> optionalUser = userRepository
                .findByUsername(userName);

        assertThat(optionalUser)
                .isPresent()
                .hasValueSatisfying(u -> assertThat(u)
                        .isEqualTo(user));

    }

    @Test
    void mustFindUserByUserNameAndPassword(){

        String userName = "userTest";
        String firstName = "user";
        String password = "passwordTest";
        String cpf = "XXXXXXXXXXX";
        Boolean enabled = true;

        User user = new User(userName, firstName,
                null,0,
                password, cpf, enabled);

        userRepository.save(user);

        Optional<User> optionalUser = userRepository
                .findByUsernameAndPassword(userName, password);

        assertThat(optionalUser)
                .isPresent()
                .hasValueSatisfying(u -> assertThat(u)
                        .isEqualTo(user));

    }


}
