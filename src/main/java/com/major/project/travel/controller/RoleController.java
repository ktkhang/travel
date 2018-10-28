package com.major.project.travel.controller;

import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.model.Role;
import com.major.project.travel.request.RoleRequest;
import com.major.project.travel.service.RoleService;
import com.major.project.travel.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * Get all roles
     * @return
     */
    @GetMapping("/all")
    public List<Role> getRoleLists() {
        return roleService.findAll();
    }

    /**
     * Get role by role name
     * @param roleName
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/show/{roleName}")
    public Role getRoleByName(@PathVariable String roleName) throws DataNotFoundException {
        return roleService.findByName(roleName);
    }

    @PostMapping("/create")
    public Role createRole(@Valid @RequestBody RoleRequest roleRequest, Errors errors) {
        Utility.validateErrorsRequest(errors);
        return roleService.createRole(roleRequest);
    }
}
