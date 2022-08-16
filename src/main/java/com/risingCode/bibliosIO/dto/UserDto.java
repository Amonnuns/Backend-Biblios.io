package com.risingCode.bibliosIO.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

    @NotBlank
    @Size(max = 20,
            message = "The username can't be more than 10 characters long")
    private String userName;

    @NotBlank
    @Size(max = 20,
            message = "The First Name can't be more than 20 characters long")
    private String firstName;

    @Size(max=50,
            message = "The last Name can't be more than 50 characters long")
    private String lastName;

    @Size(max=3,
            message = "The age can't be more than 3 characters long")
    private int age;

    @NotBlank
    @Size(max = 100,
            message = "The password can't be more than 100 characters long")
    private String password;

    @NotBlank
    @Size(max = 11,
            message = "The CPF can't be more than 11 characters long")
    private String CPF;

}
