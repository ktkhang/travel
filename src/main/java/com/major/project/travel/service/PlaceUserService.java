package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.PlaceUser;

import java.util.List;

/**
 * Created by HUY on 10/14/2018
 */
public interface PlaceUserService {

    /**
     * Create new PlaceUser
     * @param placeUser
     */
    void save(PlaceUser placeUser);

    /**
     * Update PlaceUser
     * @param placeUser
     */
    void update(PlaceUser placeUser);

    /**
     * Delete PlaceUser
     * @param placeUser
     */
    void delete(PlaceUser placeUser);

    /**
     * Get List PlaceUser
     * @return
     */
    List<PlaceUser> list();

    /**
     * findByUid
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    PlaceUser findById(Long id) throws DataNotFoundException;

    /**
     * find PlaceUser By Uid
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    PlaceUser findPlaceUserByUid(String uid) throws DataNotFoundException;
}
