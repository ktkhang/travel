package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernateInteface;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Role;

import java.io.Serializable;

public interface RoleDao extends CommonHibernateInteface<Serializable, Role> {
    /**
     * Find role by role name
     * @param roleName
     * @return
     * @throws DataNotFoundException
     */
    Role findByRoleName(String roleName) throws DataNotFoundException;
}
