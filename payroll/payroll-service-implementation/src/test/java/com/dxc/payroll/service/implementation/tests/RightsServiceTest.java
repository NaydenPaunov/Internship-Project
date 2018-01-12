package com.dxc.payroll.service.implementation.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Role;
import com.dxc.payroll.persistence.domain.UserRight;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.UserRightRepository;
import com.dxc.payroll.service.implementation.RightsServiceLogic;
import com.dxc.payroll.services.dto.RightsDTO;

/**
 *
 */
@SuppressWarnings("nls")
public class RightsServiceTest {
    /**
     * Sample right type
     */
    private static final String RIGHT_NAME = "raise";
    /**
     * Sample right description
     */
    private static final String RIGHT_DESCRIPTION = "can raise employees";
    /**
     * user right mock
     */
    private transient UserRight userRight;

    /**
     * user right repository mock
     */
    private transient UserRightRepository userRightRepository;

    /**
     * employee right repository mock
     */
    private transient EmployeeRepository employeeRepository;

    /**
     * Initializing objects with new mock repository before each test.
     */
    @Before
    public void initialize() {
        userRight = mock(UserRight.class);
        when(userRight.getTypeOfRight()).thenReturn(RIGHT_NAME);
        when(userRight.getDescription()).thenReturn(RIGHT_DESCRIPTION);
        userRightRepository = mock(UserRightRepository.class);
        employeeRepository = mock(EmployeeRepository.class);
    }

    @Test
    public void testGetAllRights() {
        final List<RightsDTO> rightDTOs = new ArrayList<>();
        rightDTOs.add(new RightsDTO(RIGHT_NAME, RIGHT_DESCRIPTION));
        final List<UserRight> userRights = new ArrayList<>();
        userRights.add(userRight);
        when(userRightRepository.getAllRights()).thenReturn(userRights);
        assertEquals("Was expecting equal DTOs", rightDTOs,
                RightsServiceLogic.getAllRights(userRightRepository));
    }

    @Test
    public void testGetRightsByUCN() {
        final String UCN = "1234567890";
        final List<RightsDTO> rightDTOs = new ArrayList<>();
        rightDTOs.add(new RightsDTO(RIGHT_NAME, RIGHT_DESCRIPTION));
        final List<UserRight> userRights = new ArrayList<>();
        userRights.add(userRight);
        final Employee employee = mock(Employee.class);
        final Role role = mock(Role.class);
        when(employeeRepository.findByUCN(UCN)).thenReturn(employee);
        when(employee.getRole()).thenReturn(role);
        when(role.getUserRights()).thenReturn(userRights);
        assertEquals(rightDTOs, RightsServiceLogic.getRights(UCN, employeeRepository));
    }

}
