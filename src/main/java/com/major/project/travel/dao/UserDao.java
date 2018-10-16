package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernateInteface;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.User;

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
}
