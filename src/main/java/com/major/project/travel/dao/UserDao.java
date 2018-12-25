package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernateInteface;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.*;
import java.util.*;

import java.io.Serializable;

/**
 * Created by HUY on 10/14/2018
 */
public interface UserDao extends CommonHibernateInteface<Serializable, User> {
    /**
     * find User By Uid
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    User findUserByUid(String uid) throws DataNotFoundException;

    /**
     * find User By UserID
     * @param userID
     * @return
     * @throws DataNotFoundException
     */
    User findUserByUserID(Long userID) throws DataNotFoundException;

    /**
     * find User By UserName and Role
     * @param userName
     * @param role
     * @return
     * @throws DataNotFoundException
     */
    User findUserByUserNameAndRole(String userName, Role role) throws DataNotFoundException;

    /**
     * find User By Place
     * @param place
     * @return
     * @throws DataNotFoundException
     */
    List<User> findByPlace(Place place) throws DataNotFoundException;

    /**
     * find all user with role
     * @return
     * @throws DataNotFoundException
     */
    List<User> findAllUserWithRole(Role role) throws DataNotFoundException;
}
