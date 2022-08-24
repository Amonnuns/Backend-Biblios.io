package com.risingCode.bibliosIO.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LibrarianDTO {

    @NotBlank
    @Size(max = 20,
            message = "The username can't be more than 10 characters long")
    private String username;

    @NotBlank
    @Size(max = 20,
            message = "The First Name can't be more than 20 characters long")
    private String firstName;

    @NotBlank
    @Size(max = 100,
            message = "The password can't be more than 100 characters long")
    private String password;

    @Size(max = 70,
            message = "Role Description can't be more than 70 characters long" )
    private String roleDescription;


    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
