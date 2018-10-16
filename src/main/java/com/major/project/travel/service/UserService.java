package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.User;

import java.util.List;

/**
 * Created by HUY on 10/14/2018
 */
public interface UserService {

    /**
     * Create new User
     * @param user
     */
    void save(User user);

    /**
     * Update User
     * @param user
     */
    void update(User user);

    /**
     * Delete User
     * @param user
     */
    void delete(User user);

    /**
     * Get List User
     * @return
     */
    List<User> list();

    /**
     * find User By Uid
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    User findUserByUid(String uid) throws DataNotFoundException;
}
