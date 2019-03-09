package com.major.project.travel.service;

import com.major.project.travel.dao.AlbumDao;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Album;
import com.major.project.travel.model.AlbumStatus;
import com.major.project.travel.request.AlbumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by HUY on 3/5/2019
 **/
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Override
    public Album save(AlbumRequest albumRequest) throws DataNotFoundException {
        Album album = new Album();
        album.setNames(albumRequest.getNames());
        album.setUrlImages(albumRequest.getUrlImages());
        album.setAlbumStatus(AlbumStatus.PUBLIC);
        albumDao.saveObj(album);
        return album;
    }
}
