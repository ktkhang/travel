package com.major.project.travel.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class LoginAdminRequest {
    @NotBlank(message = "User name must be not empty")
    private String userName;
    @NotBlank(message = "Password must be not empty")
    private String password;

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
}
