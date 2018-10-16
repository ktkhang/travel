package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernateInteface;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Place;

import java.io.Serializable;

/**
 * Created by HUY on 10/3/2018
 */
public interface PlaceDao extends CommonHibernateInteface<Serializable, Place> {
    /**
     * find Place By Uid
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    Place findPlaceByUid(String uid) throws DataNotFoundException;
}