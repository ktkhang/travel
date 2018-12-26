package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.Role;
import com.major.project.travel.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by HUY on 10/14/2018
 */
@Repository
public class UserDaoImpl extends CommonHibernate<User> implements UserDao{
    @Override
    public String getTableName() {
        return "User";
    }

    @Override
    public User findUserByUid(String uid) throws DataNotFoundException {
        String sql = "from User u where u.uid = :uid";
        try {
            Query query = getCurrentSession().createQuery(sql, User.class)
                    .setParameter("uid", uid);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            throw new DataNotFoundException("User Uid: " + uid + " is not existed.", e);
        }
    }


    @Override
    public User findUserByUserID(Long userID) throws DataNotFoundException {
        String sql = "from User u where u.userID = :userID";
        try {
            Query query = getCurrentSession().createQuery(sql, User.class)
                    .setParameter("userID", userID);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            throw new DataNotFoundException("User ID: " + userID + " is not existed.", e);
        }
    }

    @Override
    public User findUserByUserNameAndRole(String userName, Role role) throws DataNotFoundException {
        String sql = "from User u where u.userName = :userName and u.role = :role";
        try {
            Query query = getCurrentSession().createQuery(sql, User.class)
                    .setParameter("userName", userName)
                    .setParameter("role", role);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            throw new DataNotFoundException("User name: " + userName + " is not existed.", e);
        }
    }

    @Override
    public List<User> findByPlace(Place place) throws DataNotFoundException {
        String sql = "select u, pu from User u, PlaceUser pu where pu.user = u and pu.place = :place";
        try{
            Query query = getCurrentSession().createQuery(sql).setParameter("place", place);
            List<User> users = query.getResultList();
            return users;
        }catch (Exception e){
            throw new DataNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAllUserWithRole(Role role) throws DataNotFoundException {
        String sql = "from User u where u.role = :role";
        try{
            Query query = getCurrentSession().createQuery(sql, User.class)
                    .setParameter("role", role);
            List<User> users = query.getResultList();
            return users;
        }catch (Exception e){
            throw new DataNotFoundException(e.getMessage(), e);
        }
    }
}
