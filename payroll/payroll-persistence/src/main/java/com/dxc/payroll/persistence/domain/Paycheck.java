package com.dxc.payroll.persistence.domain;

import java.time.LocalDate;

/**
 * @author lmitov
 *
 */
public interface Paycheck {
    /**
     * @return the employee the Paycheck is assigned to
     */
    Employee getEmployee();

    /**
     * @return the date the paycheck was created
     */
    LocalDate getDate();

    /**
     * @return the hours worked in given Paycheck
     */
    int getHoursWorked();

    /**
     * @return the base salary
     */
    double getBaseSalary();

    /**
     * 
     * @return the gross salary
     */
    double getGrossSalary();

    /**
     * 
     * @return the tax rate
     */
    double getTaxRate();

    /**
     * 
     * @return the net salary
     */
    double getNetSalary();
}
