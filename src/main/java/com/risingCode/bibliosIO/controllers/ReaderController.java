package com.risingCode.bibliosIO.controllers;

import com.risingCode.bibliosIO.dto.UserDTO;
import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.models.Author;
import com.risingCode.bibliosIO.models.Book;
import com.risingCode.bibliosIO.models.Reader;
import com.risingCode.bibliosIO.services.ReaderService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Object> registerReader(@Valid @RequestBody UserDTO userDto){

        var user = new Reader();
        BeanUtils.copyProperties(userDto, user);
        user = readerService.registerReader(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/auth")
    public ResponseEntity<Boolean> authenticateReader(@Valid @RequestBody
                                                       UserLoginFormDto userLoginForm){

        Boolean authenticated = readerService.authenticateReader(userLoginForm);

        if (!authenticated)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);

    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getBook(@PathVariable(value = "id")
                                          UUID id){
        Book book = readerService.getBook(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(book);
    }

    @GetMapping("/books")
    public ResponseEntity<Page<Book>> getAllBooks(@PageableDefault(page = 0, size = 10, sort = "numberOfPages",
                                                                    direction = Sort.Direction.ASC )
                                                  Pageable pageable){
        Page<Book> bookPage = readerService.getAllBooks(pageable);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(bookPage);
    }

    @GetMapping("/authors")
    public ResponseEntity<Page<Author>> getAllAuthors(@PageableDefault(page = 0, size = 10,
            sort = "firstName", direction = Sort.Direction.ASC ) Pageable pageable){

        Page<Author> authorPage = readerService.getAllAuthors(pageable);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(authorPage);

    }
}
