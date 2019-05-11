package com.major.project.travel.service;

import com.major.project.travel.dao.*;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.*;
import com.major.project.travel.request.AlbumRequest;
import com.major.project.travel.request.PhotoRequest;
import com.major.project.travel.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by HUY on 3/5/2019
 **/
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private PhotoDao photoDao;

    @Autowired
    private UserRegionDao userRegionDao;

    @Autowired
    private PlaceUserDao placeUserDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private RegionDao regionDao;

    private String resourcePath = "src/main/resources/store";
    private String resource = new File(resourcePath).getAbsolutePath();

    @Override
    public Album createAlbum(AlbumRequest albumRequest) throws DataNotFoundException {
        User user = userDao.findUserByUid(albumRequest.getUserUid());

        Album newAlbum = new Album();
        newAlbum.setName(albumRequest.getName());
        newAlbum.setAlbumStatus(AlbumStatus.PUBLIC);
        System.out.println(albumRequest.getPlaceUid());

        if(albumRequest.getPlaceUid() != null){
            Place place = placeDao.findPlaceByUid(albumRequest.getPlaceUid());
            PlaceUser placeUser = placeUserDao.findByUserAndPlace(user, place);
            if (placeUser == null){
                PlaceUser placeUserNew = new PlaceUser();
                placeUserNew.setUser(user);
                placeUserNew.setPlace(place);
                try{
                    placeUserDao.saveObj(placeUserNew);
                    placeUser = placeUserDao.findByUserAndPlace(user, place);

                    Region region = regionDao.findByPlace(place);
                    if(userRegionDao.findByUserAndRegion(user, region) == null){
                        UserRegion userRegion = new UserRegion();
                        userRegion.setUser(user);
                        userRegion.setRegion(region);
                        userRegionDao.saveObj(userRegion);
                    }


                } catch (Exception e){
                    placeUser = null;
                }
            }
            newAlbum.setPlaceUser(placeUser);
        } else {
            Region region = regionDao.findRegionById(albumRequest.getRegionId());
            UserRegion userRegion = userRegionDao.findByUserAndRegion(user, region);
            if (userRegion == null){
                UserRegion newUserRegion = new UserRegion();
                newUserRegion.setUser(user);
                newUserRegion.setRegion(region);
                userRegionDao.saveObj(newUserRegion);

                userRegion = userRegionDao.findByUserAndRegion(user, region);
            }

            newAlbum.setUserRegion(userRegion);
        }
        if(!(newAlbum.getPlaceUser() == null && newAlbum.getUserRegion() == null)){

            File userFolder = new File(resource + "/" + albumRequest.getUserUid());
            System.out.println("Path exists? " + userFolder.exists());
            if(!userFolder.exists()){
                // create user folder
                userFolder.mkdir();
            }

            albumDao.saveObj(newAlbum);
            return newAlbum;
        } else {
            throw new RestException("Can not add album without region or place.", HttpServletResponse.SC_FORBIDDEN);
        }
    }

//    @Override
//    public Photo addPhoto(PhotoRequest photoRequest) throws DataNotFoundException {
//        String imgUrl = photoRequest.getPath();
//
//        Photo photo = new Photo();
//        photo.setAlbum(albumDao.findAlbumByUid(photoRequest.getAlbumUid()));
//        photo.setPhotoStatus(PhotoStatus.AVAILABLE);
//
//        BufferedImage image = null;
//        try {
//            // read file
//            File f = new File(imgUrl);
//            image = ImageIO.read(f);
//            try{
//                String photoName = getPhotoName(new File(imgUrl).getName(), photoRequest.getUserUid());
//                // write file
//                File fileClone = new File(resource + "/"+ photoRequest.getUserUid() + "/" + photoName +".png");
//                ImageIO.write(image,"png", fileClone);
//                photo.setName(photoName);
//                photo.setPath(resourcePath + "/"+ photoRequest.getUserUid() + "/" + photoName + ".png");
//
//                photoDao.saveObj(photo);
//            } catch (Exception e){
//                throw new RestException("Can not create image file.", HttpServletResponse.SC_FORBIDDEN);
//            }
//        } catch (Exception e){
//            throw new RestException("Can not read image file.", HttpServletResponse.SC_FORBIDDEN);
//        }
//
//        return photo;
//    }

    @Override
    public Photo addNewPhoto(MultipartFile file, String userUid, String albumUid) throws DataNotFoundException {
        Photo photo = new Photo();
        photo.setAlbum(albumDao.findAlbumByUid(albumUid));
        photo.setPhotoStatus(PhotoStatus.AVAILABLE);

        try{
            String photoName = getPhotoName(file.getOriginalFilename(), userUid);

            // clone file to server dir
            Path rootLocation = Paths.get(resourcePath + "/" + userUid);
            Files.copy(file.getInputStream(), rootLocation.resolve(photoName + ".png"));

            photo.setName(photoName);
//            photo.setPath(resourcePath + "/"+ userUid + "/" + photoName + ".png");
            photoDao.saveObj(photo);
        }
        catch (Exception e) {
            throw new RestException(e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return photo;
    }

    private String getPhotoName(String currentName, String userUid){
        String name = currentName;
        if (currentName.contains(".")) {
            String[] parts = currentName.split(Pattern.quote("."));
            name = parts[0];
        }
        File existFile = new File(resource + "/" + userUid + "/" + name + ".png");
        while (existFile.exists()){
            name = name + Utility.randomUid();
            existFile = new File(resource + "/" + userUid + "/" + name + ".png");
        }

        return name;
    }
}
