package com.dxc.payroll.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.dxc.payroll.persistence.domain.Role;
import com.dxc.payroll.persistence.domain.UserRight;
import com.dxc.payroll.persistence.repositories.RoleRepository;
import com.dxc.payroll.persistence.repositories.UserRightRepository;
import com.dxc.payroll.services.dto.RightMappingDTO;
import com.dxc.payroll.services.dto.RoleMappingDTO;
import com.dxc.payroll.services.dto.RoleNameDTO;

/**
 * Class with static methods used in the role service
 *
 */
public final class RoleServiceLogic {

    /**
     * this class has only static methods and should not be initialized
     */
    private RoleServiceLogic() {
        /**
         * this class has only static methods and should not be initialized
         */
    }

    /**
     * This method returns a list of all the role names in the database
     * 
     * @param rr
     * @return a list of all the role names in the database
     */
    public static List<RoleNameDTO> getRoleNames(final RoleRepository rr) {
        return roleListToRoleNameDTOList(rr.getAllRoles());
    }

    /**
     * This method takes a list of role entities and returns a list of the names
     * (IDs) of the given role entities
     *
     * @param roles
     * @return a list of DTOs that hold the names ofthe given roles
     */
    private static List<RoleNameDTO> roleListToRoleNameDTOList(final List<Role> roles) {
        final List<RoleNameDTO> roleNameDTOs = new ArrayList<>();
        for (final Role role : roles) {
            roleNameDTOs.add(new RoleNameDTO(role.getRoleName()));
        }
        return roleNameDTOs;
    }

    /**
     * A method used to fetch the relatioships between every role and every
     * right
     *
     * @return a list of DTOs holding the relation between a right and a role
     *         (true or false whether a role has a given right or not)
     */
    public static List<RoleMappingDTO> getRoleRightStateMappings(
            final RoleRepository roleRepository, final UserRightRepository urr) {
        final List<RoleMappingDTO> roleMappings = new ArrayList<>();
        for (final Role role : roleRepository.getAllRoles()) {
            final List<RightMappingDTO> rightMappingDTOs = new ArrayList<>();
            final List<UserRight> userRights = urr.getAllRights();
            for (final UserRight userRight : userRights) {
                final boolean hasThisRight = role.getUserRights().contains(userRight);
                rightMappingDTOs.add(new RightMappingDTO(userRight.getTypeOfRight(),
                        userRight.getDescription(), hasThisRight));
            }
            final RoleMappingDTO rmDTO = new RoleMappingDTO(role.getRoleName(), rightMappingDTOs);
            roleMappings.add(rmDTO);
        }
        return roleMappings;
    }
}
