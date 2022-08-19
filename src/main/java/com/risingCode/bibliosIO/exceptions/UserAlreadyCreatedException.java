package com.risingCode.bibliosIO.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Username already exists")
public class UserAlreadyCreatedException extends RuntimeException{
}
