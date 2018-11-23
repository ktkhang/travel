package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernateInteface;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.*;
import java.util.*;

import java.io.Serializable;

/**
 * Created by HUY on 10/14/2018
 */
public interface RegionDao extends CommonHibernateInteface<Serializable, Region> {
    /**
     * find Region By Uid
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    Region findRegionByUid(String uid) throws DataNotFoundException;

    /**
     * find List Region by User
     * @param user
     * @return
     * @throws DataNotFoundException
     */
    List<Region> findByUser(User user) throws DataNotFoundException;
}
