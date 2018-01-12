package com.dxc.payroll.service.implementation.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Tax;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.TaxRepository;
import com.dxc.payroll.service.exceptions.EmployeeNotFoundException;
import com.dxc.payroll.service.implementation.EmployeeServiceLogic;
import com.dxc.payroll.service.implementation.SalaryCalculator;
import com.dxc.payroll.services.dto.EmployeeDTO;

/**
 * Employee service test.
 *
 */
public class EmployeeServiceTest {
	
	//TODO: Remove transient from variables.
    /**
     * Employee mock.
     */
    private Employee employee;

    /**
     * Employee repository mock.
     */
    private transient EmployeeRepository employeeRepository;

    /**
     * Tax repository mock.
     */
    private transient TaxRepository taxRepository;

    /**
     * List of work experience.
     */
    private transient List<Tax> taxListWorkExperience;
    /**
     * List of DOD.
     */
    private transient List<Tax> taxListDOD;

    /**
     * List of all taxes.
     */
    private transient List<Tax> allTaxesList;
    /**
     * Tax mock.
     */
    private transient Tax tax;

    //TODO: Create class TaxMock instead of mockito tax.
    //TODO: Extract private methods from tests.
    //TODO: Before - only common code.
    /**
     * Mock objects.
     */
    @SuppressWarnings("nls")
    @Before
    public void initialize() {
        employee = mock(Employee.class);
        when(employee.getUCN()).thenReturn("1234567890");
        when(employee.getName()).thenReturn("Momchil");
        when(employee.getUserName()).thenReturn("Momchil");
        when(Integer.valueOf(employee.getPreviousWorkExperience())).thenReturn(Integer.valueOf(2));
        when(employee.getContractType()).thenReturn("Permanent");
        when(Integer.valueOf(employee.getWorkHours())).thenReturn(Integer.valueOf(6));
        when(employee.getTeamLeadUCN()).thenReturn(null);

        employeeRepository = mock(EmployeeRepository.class);
        taxRepository = mock(TaxRepository.class);
        tax = mock(Tax.class);
        when(tax.getTypeOfTax()).thenReturn("Work_Experience");
        when(tax.getStartDate()).thenReturn(LocalDate.parse("2004-08-10"));
        when(tax.getEndDate()).thenReturn(null);
        when(Double.valueOf(tax.getPercentageEmployee())).thenReturn(Double.valueOf(1));
        when(Double.valueOf(tax.getPercentageCompany())).thenReturn(Double.valueOf(0));
        allTaxesList = new ArrayList<>();
        taxListWorkExperience = new ArrayList<>();
        taxListWorkExperience.add(tax);
        allTaxesList.add(tax);

        taxListDOD = new ArrayList<>();
        tax = mock(Tax.class);
        when(tax.getTypeOfTax()).thenReturn("DOD");
        when(tax.getStartDate()).thenReturn(LocalDate.parse("2004-08-10"));
        when(tax.getEndDate()).thenReturn(null);
        when(Double.valueOf(tax.getPercentageEmployee())).thenReturn(Double.valueOf(10));
        when(Double.valueOf(tax.getPercentageCompany())).thenReturn(Double.valueOf(0));
        taxListDOD.add(tax);

        tax = mock(Tax.class);
        when(tax.getTypeOfTax()).thenReturn("Car_tax");
        when(tax.getStartDate()).thenReturn(LocalDate.parse("2005-08-12"));
        when(tax.getEndDate()).thenReturn(null);
        when(Double.valueOf(tax.getPercentageEmployee())).thenReturn(Double.valueOf(5));
        when(Double.valueOf(tax.getPercentageCompany())).thenReturn(Double.valueOf(0));
        allTaxesList.add(tax);
        when(tax.getTypeOfTax()).thenReturn("Car_tax");
        when(tax.getStartDate()).thenReturn(LocalDate.parse("2005-08-12"));
        when(tax.getEndDate()).thenReturn(null);
        when(Double.valueOf(tax.getPercentageEmployee())).thenReturn(Double.valueOf(5));
        when(Double.valueOf(tax.getPercentageCompany())).thenReturn(Double.valueOf(0));
        allTaxesList.add(tax);

        tax = mock(Tax.class);
        when(tax.getTypeOfTax()).thenReturn("Apartment_tax");
        when(tax.getStartDate()).thenReturn(LocalDate.parse("2004-09-10"));
        when(tax.getEndDate()).thenReturn(null);
        when(Double.valueOf(tax.getPercentageEmployee())).thenReturn(Double.valueOf(5));
        when(Double.valueOf(tax.getPercentageCompany())).thenReturn(Double.valueOf(0));
        allTaxesList.add(tax);
    }

    /**
     *
     * Tests the case when a employee is successfully found by ucn.
     *
     */
    @SuppressWarnings("nls")
    @Test
    public void testFindByUCNWithoutTeamLead() {
        final String ucn = "1234567890";
        final String name = "Momchil";
        final String username = "Momchil";
        final int workExp = 2;
        final String contractType = "Permanent";
        final int workHours = 6;
        final String teamLeadUcn = null;
        final String taxWorkExp = "Work_Experience";
        final String taxDOD = "DOD";
        when(employeeRepository.findByUCN(ucn)).thenReturn(employee);
        when(taxRepository.findUnclosedTaxesWithGivenName(taxWorkExp))
                .thenReturn(taxListWorkExperience);
        when(taxRepository.findUnclosedTaxesWithGivenName(taxDOD)).thenReturn(taxListDOD);
        when(taxRepository.getAllCurrentTaxes()).thenReturn(allTaxesList);
        when(Double.valueOf(SalaryCalculator.calculateNetSalary(ucn, employeeRepository, taxRepository)))
                .thenReturn(Double.valueOf(12));
        final EmployeeDTO employeeDTO = EmployeeServiceLogic.findEmployee(employeeRepository,
                taxRepository, ucn);

        assertEquals("Wrong employee ucn.", ucn, employeeDTO.getUcn());
        assertEquals("Employee name.", name, employeeDTO.getName());
        assertEquals("Employee username.", username, employeeDTO.getUsername());
        assertEquals("Employee work experience.", workExp, employeeDTO.getPreviousWorkExperience());
        assertEquals("Employee work hours.", workHours, employeeDTO.getWorkHours());
        assertEquals("Employee contract type.", contractType, employeeDTO.getContractType());
        assertEquals("Employee ucn.", teamLeadUcn, employeeDTO.getTeamLeadName());
    }

    /**
     *
     * Tests the case when a employee is successfully found by ucn.
     *
     */
    @SuppressWarnings("nls")
    @Test(expected = EmployeeNotFoundException.class)
    public void testFindByNoEmployee() {
        final String ucn = "0987654321";
        when(employeeRepository.findByUCN(ucn)).thenReturn(null);
        EmployeeServiceLogic.findEmployee(employeeRepository,
                taxRepository, ucn);
    }

    //TODO:Test emoloyee team lead.
    /**
     *
     * Tests the case when a employee is successfully found by ucn.
     *
     */
    @SuppressWarnings("nls")
    @Test
    public void testFindByUCNWithTeamLead() {
        when(employee.getTeamLeadUCN()).thenReturn("1234567890");

        Employee newEmployee = mock(Employee.class);
        when(newEmployee.getUCN()).thenReturn("9876543210");
        when(newEmployee.getName()).thenReturn("Ivan");
        when(newEmployee.getUserName()).thenReturn("iivan");
        when(Integer.valueOf(newEmployee.getPreviousWorkExperience()))
                .thenReturn(Integer.valueOf(2));
        when(newEmployee.getContractType()).thenReturn("Permanent");
        when(Integer.valueOf(newEmployee.getWorkHours())).thenReturn(Integer.valueOf(6));

        final String ucn = "9876543210";
        final String name = "Ivan";
        final String username = "iivan";
        final int workExp = 5;
        final String contractType = "Permanent";
        final int workHours = 9;
        final String teamLeadUcn = "1234567890";
        final String taxWorkExp = "Work_Experience";
        final String taxDOD = "DOD";
        when(employeeRepository.findByUCN(ucn)).thenReturn(employee);
        when(employeeRepository.findByUCN("1234567890")).thenReturn(newEmployee);
        when(taxRepository.findUnclosedTaxesWithGivenName(taxWorkExp))
                .thenReturn(taxListWorkExperience);
        when(taxRepository.findUnclosedTaxesWithGivenName(taxDOD)).thenReturn(taxListDOD);
        when(taxRepository.getAllCurrentTaxes()).thenReturn(allTaxesList);
        when(Double.valueOf(SalaryCalculator.calculateNetSalary(ucn, employeeRepository, taxRepository)))
                .thenReturn(Double.valueOf(12));
        final EmployeeDTO employeeDTO = EmployeeServiceLogic.findEmployee(employeeRepository,
                taxRepository, ucn);

        assertEquals("Employee ucn.", "9876543210", newEmployee.getUCN());

    }
    
    //TODO::Write test for findEmployeesByTeamLead

}
