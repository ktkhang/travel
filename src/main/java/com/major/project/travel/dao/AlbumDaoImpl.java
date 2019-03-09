package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.model.Album;
import org.springframework.stereotype.Repository;

/**
 * Created by HUY on 3/5/2019
 **/
@Repository
public class AlbumDaoImpl extends CommonHibernate<Album> implements AlbumDao {
    @Override
    public String getTableName() {
        return "Album";
    }
}
