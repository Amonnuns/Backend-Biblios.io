package com.risingCode.bibliosIO.dto;

import com.risingCode.bibliosIO.models.Author;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

public class BookDTO {

    public BookDTO(String title, int year, int numberOfPages, Set<AuthorDTO> authors) {
        this.title = title;
        this.year = year;
        this.numberOfPages = numberOfPages;
        this.authors = authors;
    }

    @NotBlank
    @Size(max = 100,
            message = "Book title can be more than 50 characters long")
    private String title;

    @Digits(integer = 4, message ="year can't be higher than 4 digits", fraction = 0)
    private int year;

    @NotNull
    @Max(value = 3000, message ="Number of pages can't be higher than 3000" )
    @Min(value = 0l, message = "Can't be a negative number")
    private int numberOfPages;

    private Set<AuthorDTO> authors = new HashSet<AuthorDTO>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Set<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDTO> authors) {
        this.authors = authors;
    }
}
