package com.dxc.payroll.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Paycheck;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.TaxRepository;
import com.dxc.payroll.service.exceptions.EmployeeNotFoundException;
import com.dxc.payroll.services.dto.PaycheckDTO;

/**
 * @author lmitov
 *
 */
public final class PaycheckServiceLogic {

    /**
     * String constant for exception message - Employee not found
     */
    @SuppressWarnings("nls")
    private static final String EMPLOYEE_NOT_FOUND = "Employee not found";

    /**
     * Private constructor for utility class
     */
    private PaycheckServiceLogic() {

    }

    /**
     * @param ucn
     * @param employeeRepository
     * @return List of paycheckDTOs of the employee with the ucn coming from the
     *         method parameters
     * @return- -null if there is no employee with that ucn -empty list if there
     *          are no paychecks for this employee
     *
     */
    public static List<PaycheckDTO> getEmployeePaychecksByUCN(final String ucn,
            final EmployeeRepository employeeRepository) {
        final Employee emp = employeeRepository.findByUCN(ucn);
        if (emp == null) {
            throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
        }
        else
            return convertToPaychecksDTO(emp.getPaychecks());
    }

    /**
     * Constructs a new PaycheckDTO
     * 
     * @param ucn
     * @param dateOfPaycheck
     * @param hoursWorked
     * @param employeeRepository
     * @param taxRepository
     * @param factory
     * @return PaycheckDTO of the newly generated paycheck
     * @throws EmployeeNotFoundException
     *             if there is no employee with that ucn
     */
    public static PaycheckDTO generatePaycheck(final String ucn, final LocalDate dateOfPaycheck,
            final int hoursWorked, final EmployeeRepository employeeRepository,
            final TaxRepository taxRepository) {
        final Employee emp = employeeRepository.findByUCN(ucn);
        if (emp == null) {
            throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
        }
        return new PaycheckDTO(ucn, emp.getBaseSalary(),
                SalaryCalculator.calculateGrossSalary(ucn, employeeRepository, taxRepository),
                SalaryCalculator.calculateNetSalary(ucn, employeeRepository, taxRepository),
                dateOfPaycheck, hoursWorked,
                SalaryCalculator.calculateTaxRateEmployee(ucn, employeeRepository, taxRepository));
    }

    /**
     * @param ucn
     * @param date
     * @param hoursWorked
     * @param grossSalary
     * @param taxRate
     * @param netSalary
     * @param baseSalary
     * @param factory
     * @return Paycheck
     */
    public static PaycheckDTO savePaycheck(final String ucn, final LocalDate date,
            final int hoursWorked, final double grossSalary, final double taxRate,
            final double netSalary, final double baseSalary, final Factory factory) {
        final EmployeeRepository employeeRepository = factory.findService(EmployeeRepository.class);
        final Employee employee = employeeRepository.findByUCN(ucn);
        if (employee == null) {
            throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
        }

        final Paycheck newPaycheck = employeeRepository.addPaycheck(employee, date, hoursWorked,
                baseSalary, grossSalary, taxRate, netSalary);
        return convertToPaycheckDTO(newPaycheck);
    }

    /**
     * Convert to paycheck DTO.
     *
     * @param paycheck
     *            the paycheck
     * @return the paycheck DTO
     */
    private static PaycheckDTO convertToPaycheckDTO(final Paycheck paycheck) {
        return new PaycheckDTO(paycheck.getEmployee().getUCN(), paycheck.getBaseSalary(),
                paycheck.getGrossSalary(), paycheck.getNetSalary(), paycheck.getDate(),
                paycheck.getHoursWorked(), paycheck.getTaxRate());
    }

    /**
     * Convert to paychecks DTO.
     *
     * @param paychecks
     *            the paychecks
     * @return the list
     */
    private static List<PaycheckDTO> convertToPaychecksDTO(final List<Paycheck> paychecks) {
        final List<PaycheckDTO> paychecksDto = new ArrayList<>();
        for (final Paycheck pay : paychecks) {
            paychecksDto.add(convertToPaycheckDTO(pay));
        }
        return paychecksDto;
    }

}
