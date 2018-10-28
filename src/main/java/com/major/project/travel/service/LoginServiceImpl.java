package com.major.project.travel.service;

import com.major.project.travel.dao.UserDao;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.User;
import com.major.project.travel.model.UserStatus;
import com.major.project.travel.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    /**
     * Handle login
     * @param loginRequest
     * @return
     */
    @Override
    public User login(LoginRequest loginRequest) throws DataNotFoundException {
        User user = null;
        try {
            user = userDao.findUserByUserID(loginRequest.getUserID());

            user.setUserName(loginRequest.getUserName());
            user.setEmail(loginRequest.getEmail());
            user.setAvatar(loginRequest.getAvatar());
            userDao.updateObj(user);
        }catch (Exception e){
            User newUser = new User();
            newUser.setUserID(loginRequest.getUserID());
            newUser.setUserName(loginRequest.getUserName());
            newUser.setEmail(loginRequest.getEmail());
            newUser.setAvatar(loginRequest.getAvatar());
            newUser.setUserStatus(UserStatus.ACTIVE);
            newUser.setRegionVisited(0);
            newUser.setPlaceVisited(0);
            userDao.saveObj(newUser);
            user = userDao.findUserByUserID(loginRequest.getUserID());
        }
        return user;
    }
}
