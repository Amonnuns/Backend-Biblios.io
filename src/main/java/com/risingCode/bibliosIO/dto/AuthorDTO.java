package com.risingCode.bibliosIO.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorDTO {

    public AuthorDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NotBlank
    @Size(max = 50,
    message = "Author first name can be more than 50 characters long")
    private String firstName;

    @Size(max = 50,
            message = "Author last name can be more than 50 characters long")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
