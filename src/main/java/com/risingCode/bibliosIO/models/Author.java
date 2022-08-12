package com.risingCode.bibliosIO.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_AUTHOR")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;


    public Author() {
    }

    public Author(UUID id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> booksWritten = new HashSet<Book>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

