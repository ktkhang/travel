package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Feeling;
import com.major.project.travel.model.PlaceUser;
import com.major.project.travel.model.User;
import com.major.project.travel.model.UserRegion;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by ktKhang on 11, Dec, 2018
 **/
@Repository
public class FeelingDaoImpl extends CommonHibernate<Feeling> implements FeelingDao{
    @Override
    public String getTableName() {
        return "Feeling";
    }

    /**
     * Find all feelings by UserRegion
     * @param userRegion
     * @return
     */
    @Override
    public List<Feeling> findAllByUserRegion(UserRegion userRegion) {
        String sql = "from Feeling f where f.userRegion = :userRegion";
        Query query = getCurrentSession().createQuery(sql)
                .setParameter("userRegion", userRegion);
        List<Feeling> feelings = query.getResultList();
        return feelings;
    }

    @Override
    public List<Feeling> findAllByPlaceUser(PlaceUser placeUser) {
        String sql = "from Feeling f where f.placeUser = :placeUser";
        Query query = getCurrentSession().createQuery(sql)
                .setParameter("placeUser", placeUser);
        List<Feeling> feelings = query.getResultList();
        return feelings;
    }

    @Override
    public List<Feeling> findPostByUser(User user) throws DataNotFoundException {
        String sql = "select distinct f from Feeling f, UserRegion ur, PlaceUser pu " +
                "where (f.userRegion = ur and ur.user = :user) or (f.placeUser = pu and pu.user = :user)";
        try {
            Query query = getCurrentSession().createQuery(sql).setParameter("user", user);
            return query.getResultList();
        }catch (Exception e){
            throw e;
        }
    }
}
