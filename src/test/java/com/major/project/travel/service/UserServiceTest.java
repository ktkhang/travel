package com.major.project.travel.service;

import com.major.project.travel.config.HibernateConfigTest;
import com.major.project.travel.config.TestBeanConfig;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.User;
import com.major.project.travel.model.UserStatus;
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
 * Created by HUY on 10/17/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestBeanConfig.class, HibernateConfigTest.class})
@WebAppConfiguration
@ActiveProfiles("testProfile")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    List<User> userList = new ArrayList<User>();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userList = this.getUserList();
    }

    @After
    public void clearUnitTest(){
        System.out.println("clearUnitTest------");
        for(User user : userList){
            userService.delete(user);
        }
    }

    @Test
    public void testFindAll(){
        System.out.println("testFindAll-------");
        List<User> users = userService.list();
    }

    @Test
    public void testSaveUser(){
        System.out.println("testSaveUser-----");
        for(User user : userList){
            userService.save(user);
        }
    }

    @Test
    public void testDeleteUser(){
        System.out.println("testDeleteuser----");
        for(User user : userList){
            userService.delete(user);
        }
    }

    public List<User> getUserList(){
        User user = new User();
        user.setUserName("quanghuy");
        user.setPlaceVisited(10);
        user.setRegionVisited(5);
        user.setUserStatus(UserStatus.ACTIVE);

        User user1 = new User();
        user.setUserName("hello");
        user.setPlaceVisited(12);
        user.setRegionVisited(7);
        user.setUserStatus(UserStatus.ACTIVE);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        return userList;
    }
}
