package com.major.project.travel.security;

import com.major.project.travel.model.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by ktKhang on 01, Nov, 2018
 **/
public class AuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -7788619177798333712L;
    private User authenticatedUser;
    private String uid;

    public AuthenticationToken(String uid){
        super(Arrays.asList());
        this.uid = uid;
    }

    public AuthenticationToken(Collection<? extends GrantedAuthority> authorities, User authenticatedUser, String uid) {
        super(authorities);
        this.authenticatedUser = authenticatedUser;
        this.uid = uid;
    }

    @Override
    public Object getCredentials() {
        return authenticatedUser;
    }

    @Override
    public Object getPrincipal() {
        return authenticatedUser;
    }

    public String getUid() {
        return uid;
    }
}
