package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Album;
import com.major.project.travel.model.Photo;
import com.major.project.travel.request.AlbumRequest;
import com.major.project.travel.request.PhotoRequest;

/**
 * Created by HUY on 3/5/2019
 **/
public interface AlbumService {

    /**
     * Create album
     * @param albumRequest
     * @return
     * @throws DataNotFoundException
     */
    Album createAlbum(AlbumRequest albumRequest) throws DataNotFoundException;

    /**
     * Add photo for album
     * @param photoRequest
     * @return
     */
    Photo addPhoto(PhotoRequest photoRequest) throws DataNotFoundException;
}
