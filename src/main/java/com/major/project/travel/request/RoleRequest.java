package com.major.project.travel.request;

import com.major.project.travel.model.RoleStatus;
import org.hibernate.validator.constraints.NotBlank;

public class RoleRequest {
    private Long id;
    @NotBlank(message = "Role name must be not empty")
    private String roleName;
    private RoleStatus roleStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleStatus getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(RoleStatus roleStatus) {
        this.roleStatus = roleStatus;
    }
}
