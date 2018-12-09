package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.PlaceUser;
import com.major.project.travel.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * Created by HUY on 10/14/2018
 */
@Repository
public class PlaceUserDaoImpl extends CommonHibernate<PlaceUser> implements PlaceUserDao{
    @Override
    public String getTableName() {
        return "PlaceUser";
    }

    @Override
    public PlaceUser findPlaceUserByUid(String uid) throws DataNotFoundException {
        String sql = "select * from tbl_place_user r where r.uid = :uid";
        try {
            Query query = getCurrentSession().createNativeQuery(sql, PlaceUser.class)
                    .setParameter("uid", uid);
            return (PlaceUser) query.getSingleResult();
        } catch (Exception e) {
            throw new DataNotFoundException("PlaceUser Uid: " + uid + " is not existed.", e);
        }
    }

    /**
     * Find PlaceUser by user and place
     * @param user
     * @param place
     * @return
     */
    @Override
    public PlaceUser findByUserAndPlace(User user, Place place) {
        String sql = "from PlaceUser pu where pu.user = :user and pu.place = :place";
        try {
            Query query = getCurrentSession().createQuery(sql, PlaceUser.class)
                    .setParameter("user", user)
                    .setParameter("place", place);
            return (PlaceUser) query.getSingleResult();
        } catch (Exception e){
            return null;
        }

    }
}
