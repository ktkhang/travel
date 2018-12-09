package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.model.Region;
import com.major.project.travel.model.User;
import com.major.project.travel.model.UserRegion;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * Created by HUY on 11/26/2018
 */
@Repository
public class UserRegionDaoImpl extends CommonHibernate<UserRegion> implements UserRegionDao {
    @Override
    public String getTableName() {
        return "UserRegion";
    }

    /**
     * Find UserRegion by user and region
     * @param user
     * @param region
     * @return
     */
    @Override
    public UserRegion findByUserAndRegion(User user, Region region) {
        String sql = "from UserRegion ur where ur.user = :user and ur.region = :region";
        try {
            Query query = getCurrentSession().createQuery(sql, UserRegion.class)
                    .setParameter("user", user)
                    .setParameter("region", region);
            return (UserRegion) query.getSingleResult();
        } catch (Exception e){
            return null;
        }

    }
}
