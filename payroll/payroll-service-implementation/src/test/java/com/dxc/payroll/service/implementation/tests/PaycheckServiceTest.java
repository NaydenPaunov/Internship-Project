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
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.service.exceptions.EmployeeNotFoundException;
import com.dxc.payroll.service.implementation.PaycheckServiceLogic;
import com.dxc.payroll.services.dto.PaycheckDTO;

/**
 * 
 * Tests for PaycheckService methods
 *
 */
@SuppressWarnings("nls")
public class PaycheckServiceTest {
    /**
     * Assert message for the case when the result must be null.
     */
    private static final String ASSERT_MESSAGE_FOR_NO_RESULT = "The result must be null";
    /**
     * EmployeeRepository mock
     */
    private transient EmployeeRepository employeeRepository;

    /**
     * Paycheck mock
     */
    private transient Paycheck paycheck;
    /**
     * List of paychecks mock.
     */
    private transient List<Paycheck> paychecks;

    /**
     * Employee mock.
     */
    private transient Employee employee;

    /**
     * Employee mock.
     */
    private transient Employee noPaychecksemployee;

    /**
     * List of Employees mock.
     */
    private transient List<Employee> employees;

    @Before
    public void initialize() {
        employee = mock(Employee.class);
        when(employee.getUCN()).thenReturn("1234567890");
        when(employee.getBaseSalary()).thenReturn(1000.00);

        noPaychecksemployee = mock(Employee.class);
        when(employee.getUCN()).thenReturn("0987654321");
        when(employee.getBaseSalary()).thenReturn(1000.00);

        employees = new ArrayList<>();
        employees.add(employee);

        paycheck = mock(Paycheck.class);
        when(paycheck.getEmployee()).thenReturn(employee);
        when(paycheck.getDate()).thenReturn(LocalDate.of(2014, 10, 1));
        when(paycheck.getGrossSalary()).thenReturn(1100.00);
        when(paycheck.getHoursWorked()).thenReturn(20);
        when(paycheck.getNetSalary()).thenReturn(900.00);
        when(paycheck.getTaxRate()).thenReturn(950.00);

        paycheck = mock(Paycheck.class);
        when(paycheck.getEmployee()).thenReturn(employee);
        when(paycheck.getDate()).thenReturn(LocalDate.of(2014, 10, 1));
        when(paycheck.getGrossSalary()).thenReturn(1100.00);
        when(paycheck.getHoursWorked()).thenReturn(20);
        when(paycheck.getNetSalary()).thenReturn(900.00);
        when(paycheck.getTaxRate()).thenReturn(950.00);

        paychecks = new ArrayList<>();
        paychecks.add(paycheck);

        employeeRepository = mock(EmployeeRepository.class);

    }

    /**
     * Tests the case when paychecks is successfully found by its ucn
     */
    @Test
    public void testGetEmployeePaychecks() {
        // given
        final String ucn = "1234567890";
        when(employeeRepository.findByUCN(ucn)).thenReturn(employee);
        // when
        final List<PaycheckDTO> paychecksDto = PaycheckServiceLogic.getEmployeePaychecksByUCN(ucn,
                employeeRepository);
        // then
        for (final PaycheckDTO pay : paychecksDto) {
            assertEquals("Employee ucn differs", ucn, pay.getEmployeeUCN());
        }

    }

    /**
     * Tests the case whene there is no employee with this ucn
     */
    @Test(expected = EmployeeNotFoundException.class)
    public void testGetEmployeePaychecksNoEmployee() {
        // given
        final String ucn = "1234567809";
        when(employeeRepository.findByUCN(ucn)).thenReturn(null);
        // when
        final List<PaycheckDTO> paychecksDto = PaycheckServiceLogic.getEmployeePaychecksByUCN(ucn,
                employeeRepository);
        // then
        assertNull(ASSERT_MESSAGE_FOR_NO_RESULT, paychecksDto);
    }

    /**
     * Tests the case when there are no paychecks for this employee.
     */
    @Test
    public void testNoPaychecksForEmployee() {
        // given
        final String ucn = "0987654321";
        when(employeeRepository.findByUCN(ucn)).thenReturn(noPaychecksemployee);
        // when
        final List<PaycheckDTO> paychecksDto = PaycheckServiceLogic.getEmployeePaychecksByUCN(ucn,
                employeeRepository);
        // then
        assertEquals("wrong paychecksDto size, expected 0", 0, paychecksDto.size());
    }

}
