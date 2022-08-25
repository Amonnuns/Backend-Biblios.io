package com.risingCode.bibliosIO.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorDTO {

    @NotBlank
    @Size(max = 50,
    message = "Author first name can be more than 50 characters long")
    private String firstName;

    @Size(max = 50,
            message = "Author last name can be more than 50 characters long")
    private String lastName;
}
