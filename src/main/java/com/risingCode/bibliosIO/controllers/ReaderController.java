package com.risingCode.bibliosIO.controllers;

import com.risingCode.bibliosIO.dto.UserDTO;
import com.risingCode.bibliosIO.dto.UserLoginFormDto;
import com.risingCode.bibliosIO.models.Reader;
import com.risingCode.bibliosIO.services.ReaderService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

        ResponseEntity<Boolean> answer = readerService.authenticateReader(userLoginForm);
        return answer;

    }
}
