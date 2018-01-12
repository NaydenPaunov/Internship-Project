package com.dxc.payroll.services;

import java.util.List;
import java.util.Map;

import com.dxc.payroll.services.dto.RoleMappingDTO;
import com.dxc.payroll.services.dto.RoleNameDTO;

/**
 * A service used to update the rights of a role and to fetch all the role
 * names(IDs)
 *
 */
public interface RoleService {
    /**
     * @return a a list of DTOs that holds the names of all the roles
     */
    List<RoleNameDTO> getRoleNames();

    /**
     * Updates the assigned rights on all roles
     * 
     * @param rightToStateMap
     *            - maps a role name to the names of the rights that this role
     *            should have but doesnt contain the roles with no rights
     * 
     */
    void updateRightsOnAllRoles(Map<String, List<String>> map);

    /**
     * A method used to fetch the relatioships between every role and every
     * right
     * 
     * @return a list of DTOs holding the relation between a right and a role
     *         (true or false whether a role has a given right or not)
     */
    List<RoleMappingDTO> getRoleRightStateMappings();
}
