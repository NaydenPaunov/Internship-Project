package com.dxc.payroll.persistence.repositories;

import java.time.LocalDate;
import java.util.List;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Paycheck;

/**
 * Interface of the main operations for the Employee.
 */
public interface EmployeeRepository {

    /**
     * Create employee in DB.
     *
     * @param UCN
     * @param name
     * @param userName
     * @param password
     * @param contractType
     * @return Employee
     */
    Employee createEmployee(String UCN, String name, String userName, String password,
            String contractType);

    /**
     * Find employee by ucn.
     *
     * @param UCN
     * @return Employee
     */
    Employee findByUCN(String UCN);

    /**
     * Find employee with parameter name.
     *
     * @param name
     * @return List<Employee>
     */
    List<Employee> findByName(String name);

    /**
     * Add a new paycheck to the database
     *
     * @param employee
     * @param date
     * @param grossSalary
     * @param netSalary
     * @param hoursWorked
     * @param baseSalary
     * @param taxRate
     * @return Paycheck
     */
    Paycheck addPaycheck(Employee employee, LocalDate date, int hoursWorked, double grossSalary,
            double taxRate, double netSalary, double baseSalary);

    /**
     * Return all employees for given team lead.
     *
     * @param teamLeadUCN
     * @return List<Employee>
     */
    List<Employee> findEmployeesByTeamLeadUCN(String teamLeadUCN);

    /**
     * Gets all employees from the database.
     * 
     * @return List<Employee>
     */
    List<Employee> getAllEmployees();
}