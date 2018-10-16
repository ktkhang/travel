package com.major.project.travel.service;

import com.major.project.travel.dao.RegionDao;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by HUY on 10/14/2018
 */
@Transactional
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;

    @Override
    public void save(Region region) {
        regionDao.saveObj(region);
    }

    @Override
    public void update(Region region) {
        regionDao.updateObj(region);
    }

    @Override
    public void delete(Region region) {
        regionDao.deleteObj(region);
    }

    @Override
    public List<Region> list() {
        return regionDao.findAll();
    }

    @Override
    public Region findRegionByUid(String uid) throws DataNotFoundException {
        return regionDao.findRegionByUid(uid);
    }
}
