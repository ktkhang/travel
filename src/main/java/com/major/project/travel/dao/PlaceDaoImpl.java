package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.model.Place;
import org.springframework.stereotype.Repository;

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
    public List<Place> findAll() {
        System.out.println("Dao");
        return super.findAll();
    }

}

