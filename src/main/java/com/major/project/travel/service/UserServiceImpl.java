package com.major.project.travel.service;

import com.major.project.travel.dao.UserDao;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void delete(User user) {
        userDao.deleteObj(user);
    }

    @Override
    public List<User> list() {
        return userDao.findAll();
    }

    @Override
    public User findUserByUid(String uid) throws DataNotFoundException {
        return userDao.findUserByUid(uid);
    }
}
