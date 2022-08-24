package com.risingCode.bibliosIO.models;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_READER")
public class Reader implements Serializable {
    private static final long serialVersionUID = 1L;

    public Reader() {
    }

    public Reader(String username, String firstName,
                  String lastName, int age, String password,
                  String CPF, String email, Boolean enabled) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.age = age;
        this.CPF = CPF;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column( length = 50)
    private String lastName;

    @Column(length = 3)
    private int age;

    @Column(nullable = false, length = 100)
    private String password;

    @Size(min = 11, max = 11, message = "CPF can't be less or more than 11 digits")
    @Column(nullable = false, length = 11)
    private String CPF;

    @Column(nullable = false, length=70)
    private String email;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean enabled;


    @OneToMany(
            mappedBy = "reader",
            cascade = CascadeType.ALL
    )
    private Set<Review> reviews = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;}


}
