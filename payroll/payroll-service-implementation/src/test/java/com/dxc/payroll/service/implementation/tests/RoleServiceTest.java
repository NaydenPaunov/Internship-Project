package com.dxc.payroll.service.implementation.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dxc.payroll.persistence.domain.Role;
import com.dxc.payroll.persistence.domain.UserRight;
import com.dxc.payroll.persistence.repositories.RoleRepository;
import com.dxc.payroll.persistence.repositories.UserRightRepository;
import com.dxc.payroll.service.implementation.RoleServiceLogic;
import com.dxc.payroll.services.dto.RightMappingDTO;
import com.dxc.payroll.services.dto.RoleMappingDTO;
import com.dxc.payroll.services.dto.RoleNameDTO;

@SuppressWarnings("nls")
public class RoleServiceTest {
    /**
     * Sample right name
     */
    private static final String RIGHT_NAME = "raise";

    /**
     * Sample role name
     */
    private static final String ROLE_NAME = "Manager";

    /**
     * Sample user right description
     */
    private static final String RIGHT_DESCRIPTION = "can raise employees";
    /**
     * role mock
     */
    private transient Role role;

    /**
     * role repository mock
     */
    private transient RoleRepository roleRepository;
    /**
     * role repository mock
     */
    private transient UserRightRepository rightRepository;
    /**
     * user right mock
     */
    private transient UserRight userRight;

    /**
     * Initializing objects with new mock repository before each test.
     */
    @Before
    public void initialize() {
        userRight = mock(UserRight.class);
        when(userRight.getTypeOfRight()).thenReturn(RIGHT_NAME);
        when(userRight.getDescription()).thenReturn(RIGHT_DESCRIPTION);
        final List<UserRight> userRights = new ArrayList<>();
        userRights.add(userRight);

        rightRepository = mock(UserRightRepository.class);
        when(rightRepository.getAllRights()).thenReturn(userRights);

        role = mock(Role.class);
        when(role.getRoleName()).thenReturn(ROLE_NAME);
        when(role.getUserRights()).thenReturn(userRights);
        roleRepository = mock(RoleRepository.class);
        final List<Role> roles = new ArrayList<>();
        roles.add(role);
        when(roleRepository.getAllRoles()).thenReturn(roles);
    }

    @Test
    public void getRoleNamesTest() {
        final RoleNameDTO roleName = new RoleNameDTO(ROLE_NAME);
        List<RoleNameDTO> list = new ArrayList<>();
        list.add(roleName);
        assertEquals("was expecting equal lists of role names", list,
                RoleServiceLogic.getRoleNames(roleRepository));
    }

    @Test
    public void getRoleRightStateMappingsTest() {
        final List<RightMappingDTO> rightMappings = new ArrayList<>();
        final RightMappingDTO rightMapping = new RightMappingDTO(RIGHT_NAME, RIGHT_DESCRIPTION,
                true);
        rightMappings.add(rightMapping);
        final RoleMappingDTO roleMapping = new RoleMappingDTO(ROLE_NAME, rightMappings);
        final List<RoleMappingDTO> roleMappings = new ArrayList<>();
        roleMappings.add(roleMapping);
        assertEquals("was expecting equal role-right mappings", roleMappings,
                RoleServiceLogic.getRoleRightStateMappings(roleRepository, rightRepository));
    }
}
