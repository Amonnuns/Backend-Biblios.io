package com.risingCode.bibliosIO.services;

import com.risingCode.bibliosIO.exceptions.UserAlreadyCreatedException;
import com.risingCode.bibliosIO.models.User;
import com.risingCode.bibliosIO.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User registerUser(User user){

        User myUser = userRepository
                .findByUserName(user.getUserName())
                .orElseThrow(UserAlreadyCreatedException::new);

        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }


}
