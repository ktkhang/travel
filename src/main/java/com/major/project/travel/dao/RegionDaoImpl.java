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
    public Region findRegionById(String id) throws DataNotFoundException {
        String sql = "from Region r where r.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, Region.class)
                    .setParameter("id", id);
            return (Region) query.getSingleResult();
        } catch (Exception e) {
            throw new RestException(String.format("Region have {%s} is not existed.", String.format("regionId = %s", id)),HttpServletResponse.SC_NOT_FOUND);
        }
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
        String sql = "select * from tbl_regions, user_region  where tbl_regions.id = user_region.region_id and user_region.user_id = :id";
        try{
            Query query = getCurrentSession().createNativeQuery(sql)
                    .addEntity(Region.class)
                    .setParameter("id", user.getId());
            List<Region> regions = query.getResultList();
            return regions;
        }catch (Exception e){
            throw new RestException("Find region error, Please contact admin to help.",HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public List<UserRegion> findByUserUid(Long id) throws DataNotFoundException {
        List<UserRegion> userRegions = null;
        String sql = "select * from tbl_user_region where tbl_user_region.user_id = :id";
        try{
            Query query = getCurrentSession().createNativeQuery(sql)
                    .addEntity(UserRegion.class)
                    .setParameter("id", id);
            userRegions = query.getResultList();
            return userRegions;
        }catch (Exception e ){
            throw new RestException("Get User_Region error, Please contact admin to help.",HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public Region findByPlace(Place place) throws DataNotFoundException {
        String sql = "select * from tbl_regions where tbl_regions.id = :id";
        try{
            Query query = getCurrentSession().createNativeQuery(sql)
                    .addEntity(Region.class)
                    .setParameter("id", place.getRegion().getId());
            return (Region) query.getResultList().get(0);
        }catch (Exception e ){
            throw new RestException("Find region by place error ", HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
