package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Place;

import java.util.List;

/**
 * Created by HUY on 10/3/2018
 */
public interface PlaceService {

    /**
     * Create new Place
     * @param place
     */
    void save(Place place);

    /**
     * Update Place
     * @param place
     */
    void update(Place place);

    /**
     * Delete Place
     * @param place
     */
    void delete(Place place);

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

}