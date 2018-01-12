package com.dxc.payroll.persistence.repositories;

import java.util.List;

import com.dxc.payroll.persistence.domain.Role;

/**
 * @author tdyakov
 *
 */
public interface RoleRepository {
    /**
     * @param roleName
     * @param description
     * @return a new role object with with given name and description
     */
    Role createRole(String roleName, String description);

    /**
     * finds a role with the given name and returns it
     * 
     * @param name
     *            a name of a role
     * @return The role with this name
     */
    Role findRoleByName(String name);

    /**
     * Returns a list of all the roles
     * 
     * @return a list of all roles
     */
    List<Role> getAllRoles();

}
