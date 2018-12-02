package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.PlaceUser;
import com.major.project.travel.request.PlaceUserRequest;

import java.util.List;

/**
 * Created by HUY on 10/14/2018
 */
public interface PlaceUserService {

    /**
     * Create new PlaceUser
     * @param placeUserRequest
     */
    PlaceUser save(PlaceUserRequest placeUserRequest) throws DataNotFoundException;

    /**
     * Update PlaceUser
     * @param placeUserRequest
     */
    PlaceUser update(PlaceUserRequest placeUserRequest) throws DataNotFoundException;

    /**
     * Delete PlaceUser
     * @param uid
     */
    String delete(String uid) throws DataNotFoundException;

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
