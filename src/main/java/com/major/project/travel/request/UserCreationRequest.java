package com.major.project.travel.request;

import org.hibernate.validator.constraints.NotBlank;

public class UserCreationRequest {
    @NotBlank(message = "Username must be not empty")
    private String userName;
    @NotBlank(message = "Password must be not empty")
    private String password;
    @NotBlank(message = "Email must be not empty")
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
