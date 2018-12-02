package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.Region;
import com.major.project.travel.model.User;
import com.major.project.travel.request.PlaceRequest;

import java.util.List;

/**
 * Created by HUY on 10/3/2018
 */
public interface PlaceService {

    /**
     * Create new Place
     * @param placeRequest
     */
    Place save(PlaceRequest placeRequest) throws DataNotFoundException;

    /**
     * Update Place
     * @param placeRequest
     */
    Place update(PlaceRequest placeRequest) throws DataNotFoundException;

    /**
     * Delete Place
     * @param uid
     */
    String delete(String uid) throws DataNotFoundException;

    /**
     * Get List Place
     * @return
     */
    List<Place> list();

    /**
     * findByUid
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    Place findById(Long id) throws DataNotFoundException;

    /**
     * find Place By Uid
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    Place findPlaceByUid(String uid) throws DataNotFoundException;

    /**
     * fin By Region
     * @param region
     * @return
     * @throws DataNotFoundException
     */
    List<Place> findByRegion(Region region) throws DataNotFoundException;

    /**
     * find By User
     * @param user
     * @return
     * @throws DataNotFoundException
     */
    List<Place> findByUser(User user) throws DataNotFoundException;
}
