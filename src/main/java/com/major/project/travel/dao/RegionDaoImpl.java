package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Region;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

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
            throw new DataNotFoundException("Region Uid: " + uid + " is not existed.", e);
        }
    }
}