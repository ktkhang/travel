package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Album;
import com.major.project.travel.request.AlbumRequest;
import com.major.project.travel.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by HUY on 3/5/2019
 **/
@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    /**
     * Save Image
     * @param albumRequest
     * @param errors
     * @return
     * @throws DataNotFoundException
     */
    @PostMapping("/saveAlbum")
    public Album saveImage(@Valid @RequestBody AlbumRequest albumRequest, Errors errors)
        throws DataNotFoundException {
        return albumService.save(albumRequest);
    }
}
