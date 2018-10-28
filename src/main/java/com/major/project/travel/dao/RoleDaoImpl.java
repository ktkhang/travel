package com.major.project.travel.dao;

import com.major.project.travel.common.CommonHibernate;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImpl extends CommonHibernate<Role> implements RoleDao {
    @Override
    public String getTableName() {
        return "Role";
    }

    @Override
    public Role findByRoleName(String roleName) throws DataNotFoundException {
        String sql = "from Role r where r.roleName = :roleName";
        try{
            Query query = getCurrentSession().createQuery(sql)
                    .setParameter("roleName", roleName);
            return (Role)query.getSingleResult();
        }catch (Exception e){
            throw new DataNotFoundException("Role: " + roleName + " is not existed.", e);
        }
    }
}
