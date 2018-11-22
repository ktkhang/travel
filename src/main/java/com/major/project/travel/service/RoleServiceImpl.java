package com.major.project.travel.service;

import com.major.project.travel.dao.RoleDao;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Role;
import com.major.project.travel.request.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService  {
    @Autowired
    private RoleDao roleDao;

    /**
     * find all roles
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * find role by role name
     * @param roleName
     * @return
     * @throws DataNotFoundException
     */
    @Override
    public Role findByName(String roleName) throws DataNotFoundException {
        return roleDao.findByRoleName(roleName);
    }

    /**
     * create role
     * @param roleRequest
     * @return
     */
    @Override
    public Role createRole(RoleRequest roleRequest) {
        Role role = new Role();
        role.setRoleName(roleRequest.getRoleName());
        role.setRoleStatus(roleRequest.getRoleStatus());
        roleDao.saveObj(role);
        return role;
    }
}
