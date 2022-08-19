package com.risingCode.bibliosIO.services;

import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.exceptions.UserAlreadyCreatedException;
import com.risingCode.bibliosIO.models.User;
import com.risingCode.bibliosIO.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User registerUser(User user){

        Optional<User> myUser = userRepository
                .findByUsername(user.getUserName());

        if (myUser.isPresent())
            throw new UserAlreadyCreatedException();

        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);

        return userRepository.save(user);
    }

    public ResponseEntity<Boolean> authenticateUser(UserLoginFormDto userLoginForm){

        Boolean authenticated = userRepository
                .findByUsername(userLoginForm.getUserName())
                .map(user -> encoder.matches(userLoginForm.getPassword(),
                            user.getPassword()))
                .orElse(false);

        if (!authenticated)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);

    }


}
