package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.Album;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by HUY on 3/5/2019
 **/
@Repository
public class AlbumDaoImpl extends CommonHibernate<Album> implements AlbumDao {
    @Override
    public String getTableName() {
        return "Album";
    }

    @Override
    public Album findAlbumByUid(String uid) throws DataNotFoundException {
        String sql = "from Album a where a.uid = :uid";
        try {
            Query query = getCurrentSession().createQuery(sql)
                    .setParameter("uid", uid);
            return (Album) query.getSingleResult();
        } catch (Exception e) {
            throw new RestException(String.format("Album have {%s} is not existed.", String.format("albumUid = %s", uid)), HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
