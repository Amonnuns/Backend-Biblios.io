package com.risingCode.bibliosIO.controllers;

import com.risingCode.bibliosIO.dto.BookDTO;
import com.risingCode.bibliosIO.dto.LibrarianDTO;
import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.models.Book;
import com.risingCode.bibliosIO.models.Librarian;
import com.risingCode.bibliosIO.services.LibrarianService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/librarian")
public class LibrarianController {

    private final LibrarianService librarianService;

    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Object> registerLibrarian(@Valid @RequestBody LibrarianDTO librarianDto){

        var librarian = new Librarian();
        BeanUtils.copyProperties(librarianDto, librarian);
        librarian = librarianService.registerLibrarian(librarian);

        return ResponseEntity.status(HttpStatus.CREATED).body(librarian);
    }

    @PostMapping("/auth")
    public ResponseEntity<Boolean> authenticateLibrarian(@Valid @RequestBody
                                                      UserLoginFormDto librarianLoginForm){

        Boolean authenticated = librarianService.authenticateLibrarian(librarianLoginForm);
        if (!authenticated)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> registerBook(@Valid @RequestBody
                                               BookDTO bookDTO){
        var book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        Book bookSaved = librarianService.registerBook(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookSaved);
    }
    @GetMapping("/book/{id}")
    public ResponseEntity<Object> getBook(@PathVariable(value = "id")
                                              UUID id){
        // TODO: 25/08/2022 service method for handling getBook
    }
}
