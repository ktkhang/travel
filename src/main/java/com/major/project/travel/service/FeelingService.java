package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Feeling;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.User;
import com.major.project.travel.request.PlaceRequest;

import java.util.List;

/**
 * Created by HUY on 12/16/2018
 */
public interface FeelingService {

    /**
     * Find by User
     * @param user
     * @return
     * @throws DataNotFoundException
     */
    List<Feeling> findPostByUser(User user) throws DataNotFoundException;

    /**
     * Create new Feeling
     * @param feeling
     */
    void save(Feeling feeling) throws DataNotFoundException;

    /**
     * Find Feeling By Place
     * @param place
     * @return
     * @throws DataNotFoundException
     */
    List<Feeling> findFeelingByPlace(Place place) throws DataNotFoundException;

}
