package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.User;
import com.major.project.travel.request.LoginRequest;

public interface LoginService {

    /**
     * Handle login
     * @param loginRequest
     * @return
     */
    User login(LoginRequest loginRequest) throws DataNotFoundException;
}
