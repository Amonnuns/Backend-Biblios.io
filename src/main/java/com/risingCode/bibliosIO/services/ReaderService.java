package com.risingCode.bibliosIO.services;

import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.exceptions.UserAlreadyCreatedException;
import com.risingCode.bibliosIO.models.Reader;
import com.risingCode.bibliosIO.repository.ReaderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final PasswordEncoder encoder;


    public ReaderService(ReaderRepository readerRepository, PasswordEncoder encoder) {
        this.readerRepository = readerRepository;
        this.encoder = encoder;
    }

    public Reader registerReader(Reader reader){

        Optional<Reader> myUser = readerRepository
                .findByUsername(reader.getUserName());

        if (myUser.isPresent())
            throw new UserAlreadyCreatedException();

        reader.setPassword(encoder.encode(reader.getPassword()));
        reader.setEnabled(true);

        return readerRepository.save(reader);
    }

    public ResponseEntity<Boolean> authenticateReader(UserLoginFormDto userLoginForm){

        Boolean authenticated = readerRepository
                .findByUsername(userLoginForm.getUserName())
                .map(user -> encoder.matches(userLoginForm.getPassword(),
                            user.getPassword()))
                .orElse(false);

        if (!authenticated)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);

    }


}
