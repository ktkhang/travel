package com.major.project.travel.service;

import com.major.project.travel.dao.PlaceDao;
import com.major.project.travel.dao.RegionDao;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.PlaceStatus;
import com.major.project.travel.model.Region;
import com.major.project.travel.model.User;
import com.major.project.travel.request.PlaceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private RegionDao regionDao;

    @Override
    public Place save(PlaceRequest placeRequest) throws DataNotFoundException{
        Place place = new Place();
        place.setName(placeRequest.getName());
        place.setTitle(placeRequest.getTitle());
        place.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place.setLatitude(placeRequest.getLatitude());
        place.setLongitude(placeRequest.getLongitude());
        place.setPlaceStatus(PlaceStatus.AVAILABLE);
        Region region = regionDao.findRegionByUid(placeRequest.getRegionUid());
        place.setRegion(region);
        placeDao.saveObj(place);
        return place;
    }

    @Override
    public Place update(PlaceRequest placeRequest) throws DataNotFoundException {
        Place place = placeDao.findPlaceByUid(placeRequest.getUid());
        place.setName(placeRequest.getName());
        place.setTitle(placeRequest.getTitle());
        place.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place.setLatitude(placeRequest.getLatitude());
        place.setLongitude(placeRequest.getLongitude());
        place.setPlaceStatus(placeRequest.getPlaceStatus());
        Region region = regionDao.findRegionByUid(placeRequest.getRegionUid());
        place.setRegion(region);
        placeDao.updateObj(place);
        return place;
    }

    @Override
    public String delete(String uid) throws DataNotFoundException{
        try {
            Place place = placeDao.findPlaceByUid(uid);
            if (place != null) {
                placeDao.deleteObj(place);
                return "Delete successfully";
            } else {
                return "Place does not exist";
            }
        } catch (DataNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RestException(String.format("Cannot delete {%s}. Please contact administrator for help.", String.format("placeUid = %s", uid)));
        }

    }

    @Override
    public List<Place> list() {
        return placeDao.findAll();
    }

    @Override
    public Place findPlaceByUid(String uid) throws DataNotFoundException {
        Place place = placeDao.findPlaceByUid(uid);
        if (place == null) {
            throw new RestException(String.format("Place have {%s} is not existed.", String.format("placeUid = %s", uid)),HttpServletResponse.SC_NOT_FOUND);
        }
        return placeDao.findPlaceByUid(uid);
    }

    @Override
    public List<Place> findByRegion(Region region) throws DataNotFoundException {
        return placeDao.findByRegion(region);
    }

    @Override
    public List<Place> findByUser(User user) throws DataNotFoundException {
        return placeDao.findByUser(user);
    }

}
