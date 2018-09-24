package com.venturedive.rotikhilao.request;

import com.venturedive.rotikhilao.enums.UserType;

import javax.validation.constraints.*;

public class SignUpRequest {

    @NotBlank
    @Size(min = 10, max = 40)
    private String username;

    @NotBlank
    @Size(min = 3, max = 15)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 15)
    private String lastName;


    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
