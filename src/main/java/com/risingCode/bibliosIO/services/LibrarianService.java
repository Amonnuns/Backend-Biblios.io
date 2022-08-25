package com.risingCode.bibliosIO.services;

import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.exceptions.UserAlreadyCreatedException;
import com.risingCode.bibliosIO.models.Book;
import com.risingCode.bibliosIO.models.Librarian;
import com.risingCode.bibliosIO.repository.BookRepository;
import com.risingCode.bibliosIO.repository.LibrarianRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

public class LibrarianService {

    private final LibrarianRepository librarianRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder encoder;


    public LibrarianService(LibrarianRepository librarianRepository,
                            BookRepository bookRepository, PasswordEncoder encoder) {
        this.librarianRepository = librarianRepository;
        this.bookRepository = bookRepository;
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

    public Boolean authenticateLibrarian(UserLoginFormDto librarianLoginForm){

        return librarianRepository
                .findByUsername(librarianLoginForm.getUserName())
                .map(librarian -> encoder.matches(librarianLoginForm.getPassword(),
                        librarian.getPassword()))
                .orElse(false);
        
    }

    public Book registerBook(Book book){
        return bookRepository.save(book);
    }

    public ResponseEntity<Object> getBook(UUID id){
        // TODO: 25/08/2022 Implement getBook 
    }
}
