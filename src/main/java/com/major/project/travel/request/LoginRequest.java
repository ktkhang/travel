package com.major.project.travel.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull(message = "User ID must be not empty")
    private Long userID;
    @NotBlank(message = "User name must be not empty")
    private String userName;
    @NotBlank(message = "Email must be not empty")
    private String email;
    @NotBlank(message = "Avatar must be not empty")
    private String avatar;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
