package com.risingCode.bibliosIO.controllers;

import com.risingCode.bibliosIO.dto.LibrarianDTO;
import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.models.Librarian;
import com.risingCode.bibliosIO.services.LibrarianService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

        ResponseEntity<Boolean> answer = librarianService.authenticateLibrarian(librarianLoginForm);
        return answer;

    }
}
