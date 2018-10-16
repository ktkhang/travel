package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Region;

import java.util.List;

/**
 * Created by HUY on 10/14/2018
 */
public interface RegionService {

    /**
     * Create new Region
     * @param region
     */
    void save(Region region);

    /**
     * Update Region
     * @param region
     */
    void update(Region region);

    /**
     * Delete Region
     * @param region
     */
    void delete(Region region);

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
}
