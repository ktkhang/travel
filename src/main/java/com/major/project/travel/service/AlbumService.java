package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Album;
import com.major.project.travel.request.AlbumRequest;

/**
 * Created by HUY on 3/5/2019
 **/
public interface AlbumService {

    /**
     * Save Image
     * @param albumRequest
     * @throws DataNotFoundException
     */
    Album save(AlbumRequest albumRequest) throws DataNotFoundException;

}
