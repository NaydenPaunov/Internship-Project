package com.dxc.payroll.services;

import java.time.LocalDate;
import java.util.List;

import com.dxc.payroll.services.dto.PaycheckDTO;

/**
 * @author lmitov
 *
 */
public interface PaycheckService {
    /**
     * @param ucn
     *            UCN of the employee
     * @return list of paychecks, associated with the employee
     */
    List<PaycheckDTO> getEmployeePaychecksByUCN(final String ucn);

    /**
     * @param employeeUCN
     * @param dateOfPaycheck
     * @param hoursWorked
     * @return PaycheckDTO of the newly created paycheck
     */
    PaycheckDTO generatePaycheck(final String employeeUCN, final LocalDate dateOfPaycheck,
            final int hoursWorked);

    /**
     * @param employeeUCN
     * @param date
     * @param hoursWorked
     * @param grossSalary
     * @param taxRate
     * @param netSalary
     * @param baseSalary
     * @return PaycheckDTO
     */
    PaycheckDTO savePaycheck(final String employeeUCN, LocalDate date, int hoursWorked,
            double grossSalary, double taxRate, double netSalary, double baseSalary);

}
