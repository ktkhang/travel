package com.major.project.travel.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.major.project.travel.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by ktKhang on 07, Nov, 2018
 **/
public class UserPrincipal implements UserDetails {
    private Long userID;
    private String uid;
    private String userName;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String avatar;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long userID, String uid, String userName, String email, String avatar, Collection<? extends GrantedAuthority> authorities) {
        this.userID = userID;
        this.uid = uid;
        this.userName = userName;
        this.email = email;
        this.avatar = avatar;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new UserPrincipal(
                user.getUserID(),
                user.getUid(),
                user.getUserName(),
                user.getEmail(),
                user.getAvatar(),
                authorities
        );
    }

    public Long getUserID() {
        return userID;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid);
    }
}
