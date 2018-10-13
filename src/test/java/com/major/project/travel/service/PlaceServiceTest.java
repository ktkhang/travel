package com.major.project.travel.service;

import com.major.project.travel.config.HibernateConfigTest;
import com.major.project.travel.config.TestBeanConfig;
import com.major.project.travel.model.Place;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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

    
}
