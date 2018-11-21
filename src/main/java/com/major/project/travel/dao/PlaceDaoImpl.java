package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.Region;
import com.major.project.travel.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by HUY on 10/3/2018
 */
@Repository
public class PlaceDaoImpl extends CommonHibernate<Place> implements PlaceDao {

    @Override
    public String getTableName() {
        return "Place";
    }

    @Override
    public Place findPlaceByUid(String uid) throws DataNotFoundException {
        String sql = "select * from tbl_places r where r.uid = :uid";
        try {
            Query query = getCurrentSession().createNativeQuery(sql, Place.class)
                    .setParameter("uid", uid);
            return (Place) query.getSingleResult();
        } catch (Exception e) {
            throw new DataNotFoundException("Place Uid: " + uid + " is not existed.", e);
        }
    }

    @Override
    public List<Place> findByRegion(Region region) throws DataNotFoundException {
        String sql = "from Place p where p.region = :region";
        Query query = getCurrentSession().createQuery(sql)
                .setParameter("region", region);
        List<Place> places = query.getResultList();
        return places;
    }

    @Override
    public List<Place> findByUser(User user) throws DataNotFoundException {
        String sql = "select p, pu from Place p, PlaceUser pu where pu.place = p and pu.user = :user";
        try{
            Query query = getCurrentSession().createQuery(sql).setParameter("user", user);
            List<Place> places = query.getResultList();
            return places;
        }catch (Exception e){
            throw new DataNotFoundException(e.getMessage(), e);
        }
    }
}

