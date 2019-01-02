package com.major.project.travel.service;

import com.major.project.travel.dao.FeelingDao;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Feeling;
import com.major.project.travel.model.Place;
import com.major.project.travel.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by HUY on 12/16/2018
 */

@Service
@Transactional
public class FeelingServiceImpl implements FeelingService {

    @Autowired
    private FeelingDao feelingDao;

    @Override
    public List<Feeling> findPostByUser(User user) throws DataNotFoundException {
        return feelingDao.findPostByUser(user);
    }

    @Override
    public void save(Feeling feeling) throws DataNotFoundException {
        feelingDao.saveObj(feeling);
    }

    @Override
    public List<Feeling> findFeelingByPlace(Place place) throws DataNotFoundException {
        return feelingDao.findFeelingByPlace(place);
    }
}
