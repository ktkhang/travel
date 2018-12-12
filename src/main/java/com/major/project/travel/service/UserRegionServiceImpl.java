package com.major.project.travel.service;

import com.major.project.travel.dao.FeelingDao;
import com.major.project.travel.dao.RegionDao;
import com.major.project.travel.dao.UserDao;
import com.major.project.travel.dao.UserRegionDao;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Feeling;
import com.major.project.travel.model.Region;
import com.major.project.travel.model.User;
import com.major.project.travel.model.UserRegion;
import com.major.project.travel.request.FeelingRegionRequest;
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

    @Autowired
    private UserDao userDao;

    @Autowired
    private RegionDao regionDao;

    @Autowired
    private FeelingDao feelingDao;

    @Override
    public void save(UserRegion userRegion) {
        userRegionDao.saveObj(userRegion);
    }

    @Override
    public void update(UserRegion userRegion) {
        userRegionDao.updateObj(userRegion);
    }

    @Override
    public UserRegion addPost(FeelingRegionRequest feelingRegionRequest) {
        User user = new User();
        try {
            user = userDao.findUserByUid(feelingRegionRequest.getUserUid());
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        Region region = new Region();
        try {
            region = regionDao.findRegionById(feelingRegionRequest.getRegionId());
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        UserRegion userRegion = userRegionDao.findByUserAndRegion(user, region);
        if (userRegion == null){
            UserRegion userRegionNew = new UserRegion();
            userRegionNew.setUser(user);
            userRegionNew.setRegion(region);
            userRegionDao.saveObj(userRegionNew);

            userRegion = userRegionDao.findByUserAndRegion(user, region);
        }

        Feeling feeling = new Feeling();
        feeling.setTopic(feelingRegionRequest.getTopic());
        feeling.setContent(feelingRegionRequest.getContent());
        feeling.setUserRegion(userRegion);
        feelingDao.saveObj(feeling);

        userRegion.setFeelings(feelingDao.findAllByUserRegion(userRegion));

        return userRegion;
    }
}
