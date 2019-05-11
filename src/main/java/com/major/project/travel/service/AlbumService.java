package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Album;
import com.major.project.travel.model.Photo;
import com.major.project.travel.request.AlbumRequest;
import com.major.project.travel.request.PhotoRequest;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;


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
     * Add photo for album version 2
     * @param file
     * @param userUid
     * @param albumUid
     * @return
     * @throws DataNotFoundException
     */
    Photo addNewPhoto(MultipartFile file, String userUid, String albumUid) throws DataNotFoundException;

}
