package com.risingCode.bibliosIO.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReviewDTO {

    public ReviewDTO(String comment, int stars, UserDTO reader, BookDTO book) {
        this.comment = comment;
        this.stars = stars;
        this.reader = reader;
        this.book = book;
    }

    @Size(max = 300,
            message = "Review can't be more than 300 characters long" )
    private String comment;

    @NotNull
    @Max(value = 5, message = "Five stars are the maximum")
    @Min(value = 1, message = "One star is the minimum")
    private int stars;

    private UserDTO reader;

    private BookDTO book;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public UserDTO getReader() {
        return reader;
    }

    public void setReader(UserDTO reader) {
        this.reader = reader;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}
