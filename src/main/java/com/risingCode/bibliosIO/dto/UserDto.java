package com.risingCode.bibliosIO.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

    @NotBlank
    @Size(max = 20,
            message = "The username can't be more than 10 characters long")
    private String username;

    @NotBlank
    @Size(max = 20,
            message = "The First Name can't be more than 20 characters long")
    private String firstName;

    @Size(max=50,
            message = "The last Name can't be more than 50 characters long")
    private String lastName;

    @Max(value = 110)
    private int age;

    @NotBlank
    @Size(max = 100,
            message = "The password can't be more than 100 characters long")
    private String password;

    @NotBlank
    @Size(max = 11,
            message = "The CPF can't be more than 11 characters long")
    private String CPF;

    public UserDto(String username, String firstName, String lastName, int age, String password, String CPF) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
        this.CPF = CPF;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
