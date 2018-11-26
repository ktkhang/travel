package com.major.project.travel.service;

import com.major.project.travel.dao.UserRegionDao;
import com.major.project.travel.model.UserRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by HUY on 11/26/2018
 */
@Transactional
@Service
public class UserRegionServiceImpl implements UserRegionService {
    @Autowired
    private UserRegionDao userRegionDao;

    @Override
    public void save(UserRegion userRegion) {
        userRegionDao.saveObj(userRegion);
    }

    @Override
    public void update(UserRegion userRegion) {
        userRegionDao.updateObj(userRegion);
    }
}
