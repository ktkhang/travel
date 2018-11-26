package com.major.project.travel.service;

import com.major.project.travel.config.HibernateConfigTest;
import com.major.project.travel.config.TestBeanConfig;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Place;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUY on 10/9/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestBeanConfig.class, HibernateConfigTest.class})
@WebAppConfiguration
@ActiveProfiles("testProfile")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlaceServiceTest {

    @Autowired
    private PlaceService placeService;

    List<Place> placeList = new ArrayList<Place>();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        System.out.println("Unit Test");
//        placeList = this.getPlaceList();
    }

//    @After
    public void clearUnitTest(){
        System.out.println("clearUnitTest------");
        for(Place place : placeList){
            placeService.delete(place);
        }
    }

    @Test
    public void testFindAll(){
        System.out.println("testFindAll-------");
        List<Place> places = placeService.list();
    }

//    @Test
    public void testSavePlace(){
        System.out.println("testSavePlace-----");
        for(Place place : placeList){
            placeService.save(place);
        }
    }

//    @Test
    public void testFindById(){
        System.out.println("testFindById-------");
        try {
            Place place = placeService.findById(1L);
        } catch (DataNotFoundException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

//    @Test
    public void testDeletePlace(){
        System.out.println("testDeletePlace----");
        for(Place place : placeList){
            placeService.delete(place);
        }
    }

    public List<Place> getPlaceList(){

        Place place = new Place();
        place.setName("Quảng Ngãi");
        place.setTitle("QuangNgai");
        place.setLongitude(1234.0);
        place.setLatitude(123.0);

        Place place1 = new Place();
        place1.setName("Bình Định");
        place1.setTitle("Binh Dinh");
        place1.setLongitude(123.0);
        place1.setLatitude(1234.0);

        List<Place> places = new ArrayList<Place>();
        places.add(place);
        places.add(place1);
        return places;
    }

}
