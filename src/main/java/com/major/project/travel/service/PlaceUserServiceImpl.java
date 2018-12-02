package com.major.project.travel.service;

import com.major.project.travel.dao.*;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.*;
import com.major.project.travel.request.PlaceUserRequest;
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
    public PlaceUser save(PlaceUserRequest placeUserRequest) throws DataNotFoundException{
        User user = null;
        Region region = null;
        Place place = null;
        PlaceUser placeUser = new PlaceUser();
        user = userDao.findUserByUid(placeUserRequest.getUserUid());
        placeUser.setUser(user);
        place = placeDao.findPlaceByUid(placeUserRequest.getPlaceUid());
        placeUser.setPlace(place);
        placeUser.setFeeling(placeUserRequest.getFeeling());
        placeUser.setAlbums(placeUserRequest.getAlbums());
        placeUser.setVideos(placeUserRequest.getVideos());

        boolean flag = false;
        placeUserDao.saveObj(placeUser);
        // Handle increase regionVisited & placeVisited return {User}
        UserRegion userRegion = new UserRegion();
        try {
            // check Region existed in user_region
            region = regionDao.findByPlace(place);
            List<UserRegion> userRegions = regionDao.findByUserUid(user.getId());

            for(UserRegion ur : userRegions){
                if(ur.getRegion().getId().equals(place.getRegion().getId())){
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
        return placeUser;
    }

    @Override
    public PlaceUser update(PlaceUserRequest placeUserRequest) throws DataNotFoundException {
        User user = null;
        Region region = null;
        Place place = null;
        PlaceUser placeUser = new PlaceUser();
        placeUser = placeUserDao.findPlaceUserByUid(placeUserRequest.getUid());
        placeUser.setFeeling(placeUserRequest.getFeeling());
        placeUser.setAlbums(placeUserRequest.getAlbums());
        placeUser.setVideos(placeUserRequest.getVideos());
        user = userDao.findUserByUid(placeUserRequest.getUserUid());
        placeUser.setUser(user);
        place = placeDao.findPlaceByUid(placeUserRequest.getPlaceUid());
        placeUser.setPlace(place);
        placeUserDao.updateObj(placeUser);
        return placeUser;
    }

    @Override
    public String delete(String uid) throws DataNotFoundException{
        try {
            PlaceUser placeUser = placeUserDao.findPlaceUserByUid(uid);
            if (placeUser != null) {
                placeUserDao.deleteObj(placeUser);
                return "Delete successfully";
            } else {
                return "PlaceUser does not exist";
            }
        } catch (DataNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RestException(String.format("Cannot delete {%s}. Please contact administrator for help.", String.format("placeUserUid = %s", uid)));
        }
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
