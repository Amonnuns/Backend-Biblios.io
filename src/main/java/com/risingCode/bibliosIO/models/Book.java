package com.risingCode.bibliosIO.models;

import javax.persistence.*;
import java.io.Serializable;
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


}
