package com.major.project.travel.service;

import com.major.project.travel.dao.UserDao;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.User;
import com.major.project.travel.model.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by HUY on 10/14/2018
 */
@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.saveObj(user);
    }

    @Override
    public void update(User user) {
        userDao.updateObj(user);
    }

    @Override
    public String delete(String uid) throws DataNotFoundException{
        try {
            User user = userDao.findUserByUid(uid);
            if (user != null) {
                user.setUserStatus(UserStatus.LOCKED);
                userDao.updateObj(user);
                return "Delete successfully";
            } else {
                return "User does not exist";
            }
        } catch (DataNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RestException(String.format("Cannot delete {%s}. Please contact administrator for help.", String.format("userUid = %s", uid)));
        }
    }

    @Override
    public List<User> list() {
        return userDao.findAll();
    }

    @Override
    public User findUserByUid(String uid) throws DataNotFoundException {
        User user = new User();
        try {
            user = userDao.findUserByUid(uid);
        }catch (Exception e){
            throw new RestException(String.format("User have {%s} is not existed.", String.format("userUid = %s", uid)),HttpServletResponse.SC_NOT_FOUND);
        }
        return user;
    }

    @Override
    public List<User> findByPlace(Place place) throws DataNotFoundException {
        return userDao.findByPlace(place);
    }
}
