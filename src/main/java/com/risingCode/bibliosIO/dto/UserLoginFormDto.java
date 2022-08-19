package com.risingCode.bibliosIO.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserLoginFormDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(max = 20,
            message = "The username can't be more than 10 characters long")
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    public UserLoginFormDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
