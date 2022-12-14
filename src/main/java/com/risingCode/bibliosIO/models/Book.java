package com.risingCode.bibliosIO.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="TB_BOOKS")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    public Book() {
    }

    public Book(UUID id, String title, int year, int numberOfPages) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.numberOfPages = numberOfPages;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(name="`year`",length = 4)
    private int year;

    @Column(nullable = false, length = 4)
    private int numberOfPages;

    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.ALL
    )
    private Set<Review> reviews = new HashSet<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "AUTHOR_BOOK",
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

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void addAuthors(Author author) {
        authors.add(author);
    }

    public void addReview(Review review){
        reviews.add(review);
    }
}
