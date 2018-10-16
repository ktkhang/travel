package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernateInteface;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.PlaceUser;

import java.io.Serializable;

/**
 * Created by HUY on 10/14/2018
 */
public interface PlaceUserDao extends CommonHibernateInteface<Serializable, PlaceUser> {
    /**
     * find PlaceUser By Uid
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    PlaceUser findPlaceUserByUid(String uid) throws DataNotFoundException;
}
