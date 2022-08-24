package com.risingCode.bibliosIO.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_LOAN")
public class Loan implements Serializable{

    private static final long serialVersionUID = 1L;

    public Loan(){ }

    public Loan(UUID id, Date loanDate, Date dueDate, String state){
        this.id = id;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.state = state;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Date loanDate;

    @Column(nullable = false)
    private Date dueDate;

    @Column(nullable = false, length = 10)
    private String state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Reader reader;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Reader getUser() {
        return reader;
    }

    public void setUser(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
