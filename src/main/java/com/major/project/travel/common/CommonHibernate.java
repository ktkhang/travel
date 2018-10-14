package com.major.project.travel.common;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.util.Utility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;


/**
 * Created by HUY on 9/9/2018
 */
public abstract class CommonHibernate<T> implements CommonHibernateInteface<Serializable, T> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveObj(Serializable t) {
        if(t instanceof CommonSerialize) {
            if(((CommonSerialize) t).getUid() == null || ((CommonSerialize) t).getUid().isEmpty()) {
                ((CommonSerialize) t).setUid(Utility.randomUid());
            }
        }
        Serializable s = sessionFactory.getCurrentSession().save(t);
    }

    @Override
    public void updateObj(Serializable t) {
        sessionFactory.getCurrentSession().update(t);
    }

    @Override
    public void deleteObj(Serializable t) {
        if(t != null){
            sessionFactory.getCurrentSession().delete(t);
        }
    }

    @Override
    public void deleteObjs(Collection<T> objs) {
        for(T obj : objs) {
            this.deleteObj((Serializable) obj);
        }
    }

    @Override
    public List<T> findAll() {
        String queryString = "from " + getTableName();
        TypedQuery<T> query = sessionFactory.getCurrentSession().createQuery(queryString);
        return query.getResultList();
    }

    @Override
    public T findObjById(Long id) throws DataNotFoundException {
        Class<T> persistentClass = (Class<T>)
                ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0]; // create class object of parametered Type first.

        try {
            return (T) sessionFactory.getCurrentSession().get(persistentClass, id);
        } catch (Exception e) {
            throw new DataNotFoundException("Data not found");
        }
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
