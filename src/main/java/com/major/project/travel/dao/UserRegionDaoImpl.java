package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.model.User;
import com.major.project.travel.model.UserRegion;
import org.springframework.stereotype.Repository;

/**
 * Created by HUY on 11/26/2018
 */
@Repository
public class UserRegionDaoImpl extends CommonHibernate<UserRegion> implements UserRegionDao {
    @Override
    public String getTableName() {
        return "UserRegion";
    }
}
