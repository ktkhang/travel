package com.major.project.travel.service;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Role;
import com.major.project.travel.request.RoleRequest;

import java.util.List;

public interface RoleService {
    /**
     * Get all roles
     * @return
     */
    List<Role> findAll();

    /**
     * Find role by role name
     * @param roleName
     * @return
     */
    Role findByName(String roleName) throws DataNotFoundException;

    /**
     * Create role
     * @param roleRequest
     * @return
     */
    Role createRole(RoleRequest roleRequest);
}
