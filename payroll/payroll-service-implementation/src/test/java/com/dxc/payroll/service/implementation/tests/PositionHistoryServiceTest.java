package com.dxc.payroll.service.implementation.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Paycheck;
import com.dxc.payroll.persistence.domain.PositionPeriod;
import com.dxc.payroll.persistence.domain.Role;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.service.implementation.PositionHistoryServiceLogic;
import com.dxc.payroll.service.implementation.tests.mocks.MockEmployee;
import com.dxc.payroll.service.implementation.tests.mocks.MockJob;
import com.dxc.payroll.service.implementation.tests.mocks.MockPosition;
import com.dxc.payroll.service.implementation.tests.mocks.MockPositionPeriod;
import com.dxc.payroll.services.dto.PositionHistoryForEmployeeDTO;

/**
 *
 */
@SuppressWarnings("nls")
public class PositionHistoryServiceTest {
    /**
     * Sample of an employee UCN
     */
    private static final String EMOLOYEE_UCN = "0123456789";
    /**
     * Correct UCN but no such employee.
     */
    private static final String CORRECT_UCN_BUT_MISSING_EMPLOYEE = "0123789456";
    /**
     * EmployeeRepository mock
     */
    private EmployeeRepository employeeRepository;

    /**
     * Initializing objects with new mock repository before each test.
     */
    @Before
    public void initialize() {
        employeeRepository = mock(EmployeeRepository.class);
    }

    /**
     *
     * Tests the case when a position history is successfully found by employee
     * UCN.
     *
     */
    @Test
    public void testFindPositionHistory() {
        final Employee employee = createMockEmployee();
        when(employeeRepository.findByUCN(EMOLOYEE_UCN)).thenReturn(employee);

        final PositionHistoryForEmployeeDTO positionHistoryForEmployeeDTO = PositionHistoryServiceLogic
                .findPositionHistory(EMOLOYEE_UCN, employeeRepository);

        assertEquals("Employee UCN has been found ", EMOLOYEE_UCN,
                positionHistoryForEmployeeDTO.getEmployeeUCN());
    }

    /**
     *
     * Tests the case when a position history is not found by employee UCN.
     *
     */
    @Test
    public void testFindPositionHistoryNoResult() {
        final PositionHistoryForEmployeeDTO positionHistoryForEmployeeDTO = PositionHistoryServiceLogic
                .findPositionHistory(CORRECT_UCN_BUT_MISSING_EMPLOYEE, employeeRepository);

        assertNull("Employee UCN has not been found and return null ",
                positionHistoryForEmployeeDTO);
    }

    /**
     * Method that creates an employee.
     *
     * @return new employee of type Employee
     */
    private static Employee createMockEmployee() {
        final Role role = null;
        final List<Paycheck> paychecks = null;
        final List<PositionPeriod> positionHistory = createPositionHistory();
        return new MockEmployee(EMOLOYEE_UCN, "Ivan Ivanov", "iivanov", "password", 0, "CT", 8,
                "1112223334", role, paychecks, positionHistory);
    }

    private static List<PositionPeriod> createPositionHistory() {
        final Role role = null;
        final List<Paycheck> paychecks = null;
        final List<PositionPeriod> positionHistory = new ArrayList<>();
        final Employee emolyee = new MockEmployee(EMOLOYEE_UCN, "Ivan Ivanov", "iivanov",
                "password", 0, "CT", 8, "1112223334", role, paychecks, positionHistory);
        positionHistory.add(new MockPositionPeriod(LocalDate.now(), LocalDate.now(), 2000.00,
                new MockPosition(new MockJob("Developer", "Junior", 20, 1200.00, 3000.00), 4,
                        1800.00),
                emolyee));
        return positionHistory;
    }

}
