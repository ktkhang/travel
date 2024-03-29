package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernateInteface;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Album;

import java.io.Serializable;

/**
 * Created by HUY on 3/5/2019
 **/
public interface AlbumDao extends CommonHibernateInteface<Serializable, Album> {

    /**
     * Get album by albumUid
     * @param uid
     * @return
     * @throws DataNotFoundException
     */
    Album findAlbumByUid(String uid) throws DataNotFoundException;
}
