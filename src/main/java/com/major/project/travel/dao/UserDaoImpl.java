package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

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
        String sql = "select * from tbl_users r where r.uid = :uid";
        try {
            Query query = getCurrentSession().createNativeQuery(sql, User.class)
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
}
