package com.dxc.payroll.service.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.persistence.domain.Role;
import com.dxc.payroll.persistence.domain.UserRight;
import com.dxc.payroll.persistence.repositories.RoleRepository;
import com.dxc.payroll.persistence.repositories.UserRightRepository;
import com.dxc.payroll.persistence.utils.TransactionHandler;
import com.dxc.payroll.services.RoleService;
import com.dxc.payroll.services.dto.RoleMappingDTO;
import com.dxc.payroll.services.dto.RoleNameDTO;

/**
 * This service has methods for fetching the IDs of all the roles and updating
 * the assigned rights of all the roles
 *
 * @author tdyakov
 *
 */
public class RoleServiceImpl extends AbstractService implements RoleService {

    /**
     * Instantiates a new Role service
     *
     * @param transactionHandler
     */
    public RoleServiceImpl(final TransactionHandler transactionHandler) {
        super(transactionHandler);
    }

    /**
     * This method returns a list of DTOs that hold the names of the roles. The
     * names are IDs for the role entity
     *
     * @return a list of DTOs that holds the names of all the roles
     */
    @Override
    public List<RoleNameDTO> getRoleNames() {
        return transactionHandler.execute((final Factory factory) -> RoleServiceLogic
                .getRoleNames(factory.findService(RoleRepository.class)));
    }

    /**
     * Updates the assigned rights on all roles. The missing role names are
     * added as keys to the given map with empty lists as values.
     *
     * @param parameterNames
     *            - maps a role name to the names of the rights that this role
     *            should have but doesnt contain the roles with no rights
     *
     */
    @Override
    public void updateRightsOnAllRoles(final Map<String, List<String>> map) {
        transactionHandler.execute((final Factory factory) -> {
            final RoleRepository roleRepository = factory.findService(RoleRepository.class);
            for (final Role role : roleRepository.getAllRoles()) {
                if (!map.containsKey(role.getRoleName())) {
                    map.put(role.getRoleName(), Collections.emptyList());
                }
            }
            for (final Entry<String, List<String>> entry : map.entrySet()) {
                updateRightsToRole(factory, entry.getKey(), entry.getValue());
            }
            return null;
        });
    }

    /**
     * This method updates the rights to a single role with the given list of
     * rights
     *
     * @param roleName
     *            the name of the role (ID)
     * @param rightNames
     *            the names (IDs) of the rights to be assigned to the role
     * @return a RoleNameDTO holding the given role name
     */
    private static RoleNameDTO updateRightsToRole(final Factory factory, final String roleName,
            final List<String> rightNames) {
        final UserRightRepository userRightRepository = factory
                .findService(UserRightRepository.class);
        final List<UserRight> userRights = new ArrayList<>();
        for (final String name : rightNames) {
            userRights.add(userRightRepository.findRightByName(name));
        }
        final RoleRepository roleRepository = factory.findService(RoleRepository.class);
        roleRepository.findRoleByName(roleName).updateUserRights(userRights);
        return new RoleNameDTO(roleName);
    }

    /**
     * A method used to fetch the relatioships between every role and every
     * right
     *
     * @return a list of DTOs holding the relation between a right and a role
     *         (true or false whether a role has a given right or not)
     */
    @Override
    public List<RoleMappingDTO> getRoleRightStateMappings() {
        return transactionHandler.execute((final Factory factory) -> RoleServiceLogic
                .getRoleRightStateMappings(factory.findService(RoleRepository.class),
                        factory.findService(UserRightRepository.class)));
    }

}
