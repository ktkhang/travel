package com.major.project.travel.service;

import com.major.project.travel.model.*;

/**
 * Created by HUY on 11/26/2018
 */
public interface UserRegionService {
    /**
     * Create new UserRegion
     * @param userRegion
     */
    void save(UserRegion userRegion);

    /**
     * Update UserRegion
     * @param userRegion
     */
    void update(UserRegion userRegion);
}
