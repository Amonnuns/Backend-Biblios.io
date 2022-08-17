package com.risingCode.bibliosIO.controllers;

import com.risingCode.bibliosIO.dto.UserDto;
import com.risingCode.bibliosIO.models.User;
import com.risingCode.bibliosIO.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDto userDto){

        var user = new User();
        BeanUtils.copyProperties(userDto, user);
        user = userService.registerUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
