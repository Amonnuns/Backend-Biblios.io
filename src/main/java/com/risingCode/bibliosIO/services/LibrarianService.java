package com.risingCode.bibliosIO.services;

import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.exceptions.UserAlreadyCreatedException;
import com.risingCode.bibliosIO.models.Librarian;
import com.risingCode.bibliosIO.repository.LibrarianRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class LibrarianService {

    private final LibrarianRepository librarianRepository;
    private final PasswordEncoder encoder;


    public LibrarianService(LibrarianRepository librarianRepository, PasswordEncoder encoder) {
        this.librarianRepository = librarianRepository;
        this.encoder = encoder;
    }

    public Librarian registerLibrarian(Librarian librarian){

        Optional<Librarian> myUser = librarianRepository
                .findByUsername(librarian.getUsername());

        if (myUser.isPresent())
            throw new UserAlreadyCreatedException();

        librarian.setPassword(encoder.encode(librarian.getPassword()));
        librarian.setEnabled(true);

        return librarianRepository.save(librarian);
    }

    public ResponseEntity<Boolean> authenticateLibrarian(UserLoginFormDto librarianLoginForm){

        Boolean authenticated = librarianRepository
                .findByUsername(librarianLoginForm.getUserName())
                .map(librarian -> encoder.matches(librarianLoginForm.getPassword(),
                        librarian.getPassword()))
                .orElse(false);

        if (!authenticated)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);

    }
}
