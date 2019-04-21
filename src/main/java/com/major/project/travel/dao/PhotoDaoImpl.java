package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.model.Photo;
import org.springframework.stereotype.Repository;

@Repository
public class PhotoDaoImpl extends CommonHibernate<Photo> implements PhotoDao  {
    @Override
    public String getTableName() {
        return "Photo";
    }

}
