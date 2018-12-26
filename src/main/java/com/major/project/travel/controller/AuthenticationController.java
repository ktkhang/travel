package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.User;
import com.major.project.travel.request.LoginAdminRequest;
import com.major.project.travel.request.LoginRequest;
import com.major.project.travel.security.JwtProvider;
import com.major.project.travel.service.LoginService;
import com.major.project.travel.util.Constant;
import com.major.project.travel.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest userRequest, Errors errors) {
        try {
            // validate input
            Utility.validateErrorsRequest(errors);
            User user = loginService.login(userRequest);

            String jwt = tokenProvider.generateToken(user);
            String token = Constant.BEARER_STRING + jwt;
            return token;
        } catch (Exception ex) {
            throw new RestException("You do not have permission to login", HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @PostMapping("/admin/login")
    public String loginAdmin(@Valid @RequestBody LoginAdminRequest adminRequest, Errors errors) {
        try {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(adminRequest.getUserName(), adminRequest.getPassword());
            authenticationManager.authenticate(authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateTokenForAdmin(authenticationManager.authenticate(authentication));
            String token = Constant.BEARER_STRING + jwt;
            return token;
        } catch (Exception ex) {
            throw new RestException("Username or password is incorrect", HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
