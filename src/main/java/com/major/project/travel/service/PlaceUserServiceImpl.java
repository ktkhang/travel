package com.major.project.travel.service;

import com.major.project.travel.dao.PlaceUserDao;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.PlaceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Created by HUY on 10/14/2018
 */
@Transactional
@Service
public class PlaceUserServiceImpl implements PlaceUserService{

    @Autowired
    private PlaceUserDao placeUserDao;

    @Override
    public void save(PlaceUser placeUser) {
        try{
            placeUserDao.saveObj((Serializable) placeUser);
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public void update(PlaceUser placeUser) {
        placeUserDao.updateObj((Serializable) placeUser);
    }

    @Override
    public void delete(PlaceUser placeUser) {
        placeUserDao.deleteObj((Serializable) placeUser);
    }

    @Override
    public List<PlaceUser> list() {
        return placeUserDao.findAll();
    }

    @Override
    public PlaceUser findById(Long id) throws DataNotFoundException {
        return placeUserDao.findObjById(id);
    }

    @Override
    public PlaceUser findPlaceUserByUid(String uid) throws DataNotFoundException {
        return placeUserDao.findPlaceUserByUid(uid);
    }
}
