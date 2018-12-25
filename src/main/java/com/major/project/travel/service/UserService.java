package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.User;
import com.major.project.travel.request.UserCreationRequest;

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
     * @param uid
     */
    String delete(String uid) throws DataNotFoundException;

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

    /**
     * find User By Uid
     * @param username
     * @return
     * @throws DataNotFoundException
     */
    User findUserAdminByName(String username) throws DataNotFoundException;

    /**
     * find User By Place
     * @param place
     * @return
     * @throws DataNotFoundException
     */
    List<User> findByPlace(Place place) throws DataNotFoundException;

    /**
     * create user with role admin
     * @param userCreationRequest
     * @return
     */
    User createAdminUser(UserCreationRequest userCreationRequest) throws DataNotFoundException;
}
