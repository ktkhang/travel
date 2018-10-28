package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.User;
import com.major.project.travel.request.LoginRequest;
import com.major.project.travel.service.LoginService;
import com.major.project.travel.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public User login(@Valid @RequestBody LoginRequest userRequest, Errors errors) throws DataNotFoundException {
        // validate input
        Utility.validateErrorsRequest(errors);
        return loginService.login(userRequest);
    }
}
