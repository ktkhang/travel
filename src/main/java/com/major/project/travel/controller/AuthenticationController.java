package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.User;
import com.major.project.travel.request.LoginRequest;
import com.major.project.travel.security.JwtProvider;
import com.major.project.travel.service.LoginService;
import com.major.project.travel.util.Constant;
import com.major.project.travel.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by ktKhang on 31, Oct, 2018
 **/

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtProvider tokenProvider;

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest userRequest, Errors errors) throws DataNotFoundException {
        try {
            // validate input
            Utility.validateErrorsRequest(errors);
            User user = loginService.login(userRequest);

            String jwt = tokenProvider.generateToken(user);
            String token = Constant.BEARER_STRING + jwt;
            return token;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
