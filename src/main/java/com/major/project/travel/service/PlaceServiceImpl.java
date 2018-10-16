package com.major.project.travel.service;

import com.major.project.travel.dao.PlaceDao;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by HUY on 10/3/2018
 */
@Transactional
@Service
public class PlaceServiceImpl implements PlaceService{

    @Autowired
    private PlaceDao placeDao;

    @Override
    public void save(Place place) {
        placeDao.saveObj(place);
    }

    @Override
    public void update(Place place) {
        placeDao.updateObj(place);
    }

    @Override
    public void delete(Place place) {
        placeDao.deleteObj(place);
    }

    @Override
    public List<Place> list() {
        return placeDao.findAll();
    }

    @Override
    public Place findById(Long id) throws DataNotFoundException {
        return placeDao.findObjById(id);
    }

    @Override
    public Place findPlaceByUid(String uid) throws DataNotFoundException {
        return placeDao.findPlaceByUid(uid);
    }

}
