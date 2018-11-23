package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Region;
import com.major.project.travel.model.User;
import com.major.project.travel.request.RegionRequest;

import java.util.List;

/**
 * Created by HUY on 10/14/2018
 */
public interface RegionService {

    Region create(RegionRequest regionRequest);

    /**
     * Get List Region
     * @return
     */
    List<Region> list();

    /**
     * find Region By Uid
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    Region findRegionByUid(String uid) throws DataNotFoundException;

    /**
     * create sample data
     */
    List<Region> createSampleData();

    /**
     * find List Region by User
     * @param user
     * @return
     * @throws DataNotFoundException
     */
    List<Region> findByUser(User user) throws DataNotFoundException;
}
