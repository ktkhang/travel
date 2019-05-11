package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Album;
import com.major.project.travel.model.Photo;
import com.major.project.travel.request.AlbumRequest;
import com.major.project.travel.request.PhotoRequest;
import com.major.project.travel.service.AlbumService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HUY on 3/5/2019
 **/
@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    /**
     * create album
     * @param albumRequest
     * @param errors
     * @return
     * @throws DataNotFoundException
     */
    @PostMapping("/createAlbum")
    public Album createAlbum(@Valid @RequestBody AlbumRequest albumRequest, Errors errors) throws DataNotFoundException {
        return albumService.createAlbum(albumRequest);
    }

    @PostMapping("/addNewPhoto")
    public Photo addNewPhoto(@RequestParam("file") MultipartFile file,
                              @RequestParam("userUid") String userUid,
                              @RequestParam("albumUid") String albumUid) throws DataNotFoundException{

        return albumService.addNewPhoto(file, userUid, albumUid);
    }

    @PostMapping("/loadPhoto")
    public String loadPhoto() throws UnsupportedEncodingException {
        String resourcePath = "src/main/resources/store";
        String resource = new File(resourcePath).getAbsolutePath();

        File file = new File(resource + "/" + "w7xm3deyFJHQ" + "/" + "20190211_150055" + ".png");
        InputStream in = null;
        try {
            in = new DataInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error! -> message = " + e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
//        InputStream in = getClass().getResourceAsStream(resource + "/w7xm3deyFJHQ/20190211_150055.png");
        System.out.println(in);
        byte[] media = new byte[0];
        try {
            media = IOUtils.toByteArray(in);
        } catch (IOException e) {
            throw new RuntimeException("Error! -> message = " + e.getMessage());
        }
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//        headers.setContentType(MediaType.APPLICATION_STREAM_JSON);


        byte[] encodeBase64 = Base64.getEncoder().encode(media);
        String base64Encoded = new String(encodeBase64, "UTF-8");

        System.out.println(base64Encoded);

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);

//        return responseEntity;

//        return ResponseEntity
//                .ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .body(new InputStreamResource(in));

        return base64Encoded;
    }
}
