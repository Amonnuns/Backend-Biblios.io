package com.risingCode.bibliosIO.dto;

import com.risingCode.bibliosIO.models.Author;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class BookDTO {

    @NotBlank
    @Size(max = 50,
            message = "Book title can be more than 50 characters long")
    private String title;

    @Size(max = 4,
            message = "Year can be more than 4 characters long")
    private int year;

    @NotBlank
    @Size(max = 4,
            message = "Number of pages can be more than 4 characters long")
    private int numberOfPages;

    private Set<AuthorDTO> authors = new HashSet<AuthorDTO>();
}
