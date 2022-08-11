package com.risingCode.bibliosIO.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(UUID id, String userName, String firstName, String lastName, int age, String passwordHash, String CPF, String salt) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.passwordHash = passwordHash;
        this.CPF = CPF;
        this.salt = salt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column( length = 50)
    private String lastName;

    @Column(length = 3)
    private int age;

    @Column(nullable = false, length = 100)
    private String passwordHash;

    @Column(nullable = false, length = 15)
    private String CPF;

    @Column(nullable = false, length = 20)
    private String salt;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new HashSet<Review>();

}
