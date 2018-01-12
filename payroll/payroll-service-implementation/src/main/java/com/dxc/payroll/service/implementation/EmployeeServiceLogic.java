package com.dxc.payroll.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Role;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.TaxRepository;
import com.dxc.payroll.service.exceptions.EmployeeNotFoundException;
import com.dxc.payroll.services.dto.EmployeeDTO;

/**
 * Employee service logic.
 */
public final class EmployeeServiceLogic {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Private constructor of utility class.
     */
    private EmployeeServiceLogic() {
    }

    /**
     * Find employee by ucn.If there is no employee with given ucn the method
     * will throw EmployeeNotFoundException with appropriate message.
     *
     * @param employeeRepository
     * @param ucn
     * @param taxRepository
     * @return EmployeeDTO
     */
    @SuppressWarnings("nls")
    public static EmployeeDTO findEmployee(final EmployeeRepository employeeRepository,
            final TaxRepository taxRepository, final String ucn) {
        final Employee employee = employeeRepository.findByUCN(ucn);
        if (employee == null) {
            final String errorMessage = "Employee with ucn" + ucn + " does not exists.";
            logger.log(Level.WARNING, errorMessage);
            throw new EmployeeNotFoundException(errorMessage);
        }

        final String teamLeadUCN = employee.getTeamLeadUCN();
        String teamLeadName = null;
        if (teamLeadUCN != null) {
            final Employee teamLeadEmployee = employeeRepository.findByUCN(teamLeadUCN);
            teamLeadName = teamLeadEmployee.getName();
        }

        final double netSalary = SalaryCalculator.calculateNetSalary(ucn, employeeRepository, taxRepository);

        return employeeToEmployeeDTO(employee, teamLeadName, netSalary);
    }

    /**
     * Gets list of all employees, which are currently present in the database.
     *
     * @param employeeRepository
     *
     * @param taxRepository
     * @return list of all Employees
     */
    @SuppressWarnings("nls")
    public static List<EmployeeDTO> getAllEmployees(final EmployeeRepository employeeRepository,
            final TaxRepository taxRepository) {
        final List<Employee> employees = employeeRepository.getAllEmployees();
        if (employees.isEmpty()) {
            logger.log(Level.WARNING, "There are no employees in the database.");
        }

        return convertListEmployeeToEmployeeDTO(employees, employeeRepository, taxRepository);
    }

    /**
     * Find list of employee by team lead ucn.If there is no employee with given
     * ucn the method will throw EmployeeNotFoundException with appropriate
     * message.If the employee with given ucn has no subordinate the method will
     * throw EmployeeNotFoundExpetion with appripriate message.
     *
     * @param employeeRepository
     * @param taxRepository
     * @param ucn
     * @throws EmployeeNotFoundException
     * @return List<EmployeeDTO>
     */
    @SuppressWarnings("nls")
    public static List<EmployeeDTO> findEmployeeByTeamLeadUCN(
            final EmployeeRepository employeeRepository,
            final TaxRepository taxRepository, final String ucn) {
        final Employee employee = employeeRepository.findByUCN(ucn);

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with ucn " + ucn + " doesn't exists.");
        }
        else {

            final List<Employee> employeeList = employeeRepository.findEmployeesByTeamLeadUCN(ucn);
            if (employeeList.isEmpty()) {
                throw new EmployeeNotFoundException(
                        "Employee with ucn " + ucn + " is not a team lead.");
            }

            return convertListEmployeeToSubordinate(employeeList, employeeRepository, taxRepository,
                    employee.getName());
        }
    }

    /**
     * Convert list of employee entity to list of EmployeeDTO's.
     *
     * @param employeeList
     * @param employeeRepository
     * @param taxRepository
     * @param employeeName
     * @return List<EmployeeDTO>
     */
    private static List<EmployeeDTO> convertListEmployeeToSubordinate(
            final List<Employee> employeeList,
            final EmployeeRepository employeeRepository, final TaxRepository taxRepository,
            final String employeeName) {
        final List<EmployeeDTO> subordinateList = new ArrayList<>();

        for (final Employee employee : employeeList) {
            final double netSalary = SalaryCalculator.calculateNetSalary(employee.getUCN(),
                    employeeRepository, taxRepository);
            final EmployeeDTO subordinate = employeeToEmployeeDTO(employee, employeeName,
                    netSalary);
            subordinateList.add(subordinate);
        }

        return subordinateList;
    }

    /**
     * Convert list of JpaEmployee to list of EmployeeDTO's.
     *
     * @param employeeList
     * @param factory
     * @return List<EmployeeDTO>
     */
    private static List<EmployeeDTO> convertListEmployeeToEmployeeDTO(
            final List<Employee> employeeList,
            final EmployeeRepository employeeRepository, final TaxRepository taxRepository) {
        final List<EmployeeDTO> employees = new ArrayList<>();

        for (final Employee employee : employeeList) {
            final double netSalary = SalaryCalculator.calculateNetSalary(employee.getUCN(),
                    employeeRepository, taxRepository);
            final EmployeeDTO employeeDTO = employeeToEmployeeDTO(employee, employee.getName(),
                    netSalary);
            employees.add(employeeDTO);
        }

        return employees;
    }

    /**
     * Convert Employee to EmployeeDTO.
     *
     * @param employee
     * @param netSalary
     * @return EmployeeDTO
     */
    private static EmployeeDTO employeeToEmployeeDTO(final Employee employee,
            final String teamLeadName,
            final double netSalary) {
        assert employee != null;

        final String roleName = validateRole(employee);

        return new EmployeeDTO(employee.getName(), employee.getUCN(), employee.getUserName(),
                employee.getPreviousWorkExperience(), employee.getContractType(),
                employee.getWorkHours(), teamLeadName,
                roleName, netSalary);
    }

    /**
     * This method checks whether the employee has role, because the simple
     * employees do not have role.
     *
     * @param employee
     * @return String
     */
    private static String validateRole(final Employee employee) {
        String roleName = null;
        final Role role = employee.getRole();
        if (role != null) {
            roleName = role.getRoleName();
        }

        return roleName;
    }

}
