package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.User;
import com.major.project.travel.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by ktKhang on 31, Oct, 2018
 **/

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userUid) throws UsernameNotFoundException {
        try {
            User user = this.userService.findUserByUid(userUid);
            UserDetails userDetails = UserPrincipal.create(user);
            return userDetails;
        } catch (Exception ex) {
            try {
                System.out.println("======== userDetailsService");
                User user = this.userService.findUserAdminByName(userUid);
                UserDetails userDetails = UserPrincipal.create(user);
                System.out.println(user.getPassword());
                return userDetails;
            } catch (DataNotFoundException e) {
//                ex.printStackTrace();
                throw new UsernameNotFoundException(ex.getMessage(), ex);
            }
        }
    }
}
