package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by HUY on 10/14/2018
 */
@Repository
public class RegionDaoImpl extends CommonHibernate<Region> implements RegionDao {
    @Override
    public String getTableName() {
        return "Region";
    }

    @Override
    public Region findRegionByUid(String uid) throws DataNotFoundException {
        String sql = "select * from tbl_regions r where r.uid = :uid";
        try {
            Query query = getCurrentSession().createNativeQuery(sql, Region.class)
                    .setParameter("uid", uid);
            return (Region) query.getSingleResult();
        } catch (Exception e) {
            throw new RestException(String.format("Region have {%s} is not existed.", String.format("regionUid = %s", uid)),HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public List<Region> findByUser(User user) throws DataNotFoundException {
        System.out.println("User ID : " + user.getId());
        //String sql = "select r from tbl_regions r, user_region ur where ur.region_id = r.id and ur.user_id = :id";
        String sql = "select * from tbl_regions, user_region  where tbl_regions.id = user_region.region_id and user_region.user_id = :id";
        try{
            System.out.println("Prepare Query");
            Query query = getCurrentSession().createNativeQuery(sql)
                    .addEntity(Region.class)
                    .setParameter("id", user.getId());
            System.out.println("========");
            List<Region> regions = query.getResultList();
            return regions;
        }catch (Exception e){
            throw new RestException("Find region error, Please contact admin to help.",HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
