package com.risingCode.bibliosIO.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_REVIEW")
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    public Review() {
    }

    public Review(UUID id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 300)
    private String comment;

    @Column(length = 5)
    private int stars;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Reader reader;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Reader getUser() {
        return reader;
    }

    public void setUser(Reader reader) {
        this.reader = reader;
    }


}
