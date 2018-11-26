package com.major.project.travel.service;

import com.major.project.travel.dao.*;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private UserDao userDao;

    @Autowired
    private RegionDao regionDao;

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private UserRegionDao userRegionDao;

    @Override
    public void save(PlaceUser placeUser) {
        boolean flag = false;
        placeUserDao.saveObj((Serializable) placeUser);
        // Handle increase regionVisited & placeVisited return {User}
        User user = null;
        UserRegion userRegion = new UserRegion();
        Region region = null;
        try {
            user = userDao.findUserByUid(placeUser.getUser().getUid());
            // check Region existed in user_region
            Place place = placeDao.findPlaceByUid(placeUser.getPlace().getUid());
            region = regionDao.findByPlace(place);
            List<UserRegion> userRegions = regionDao.findByUserUid(user.getId());

            for(UserRegion ur : userRegions){
                System.out.println("2=============" + ur.getUser().getId());
                System.out.println("3=============" + ur.getRegion().getId());
                if(ur.getRegion().getId().equals(place.getRegion().getId())){
                    System.out.println("flag=true");
                    flag = true;
                }
            }
        } catch (DataNotFoundException e) {
            throw new RestException(String.format(
                    "User have {%s} is not existed.",String.format("uid = %s",String.format(placeUser.getUser().getUid()))),
                    HttpServletResponse.SC_NOT_FOUND);
        }

        if(flag){
            // case Region existed in user_region
            user.setPlaceVisited(user.getPlaceVisited() + 1);
            userDao.updateObj(user);
        }else{
            // case Region not existed in user_region
            // Update for tbl_users (regionVisited & placeVisited)
            user.setPlaceVisited(user.getPlaceVisited() + 1);
            user.setRegionVisited(user.getRegionVisited() + 1);
            userDao.updateObj(user);

            userRegion.setUser(user);
            userRegion.setRegion(region);
            if(userRegion != null){
                userRegionDao.saveObj(userRegion);
            }
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
