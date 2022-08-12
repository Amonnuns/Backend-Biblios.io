package com.risingCode.bibliosIO.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="TB_BOOKS")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    public Book() {
    }

    public Book(UUID id, String title, int year, int pageNumber) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.pageNumber = pageNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 4)
    private int year;

    @Column(length = 4)
    private int pageNumber;

    @ManyToMany
    @JoinTable(
            name = "AUTHOR_BOOKS",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<Author>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
