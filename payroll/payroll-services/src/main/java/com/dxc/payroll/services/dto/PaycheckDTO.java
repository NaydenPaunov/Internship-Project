package com.dxc.payroll.services.dto;

import java.time.LocalDate;

/**
 * Paycheck DTO for client-side presentation of the Paycheck entity
 *
 */
public class PaycheckDTO {
    /**
     * employeeUCN for DTO
     */
    private final String employeeUCN;
    /**
     * baseSalary for DTO
     */
    private final double baseSalary;
    /**
     * grossSalary for DTO
     */
    private final double grossSalary;
    /**
     * netSalary for DTO
     */
    private final double netSalary;
    /**
     * dateOfPaycheck for DTO
     */
    private final LocalDate dateOfPaycheck;
    /**
     * hoursWorked for DTO
     */
    private final int hoursWorked;

    /**
     * taxRate for DTO
     */
    private final double taxRate;

    /**
     * Constructor of Paycheck DTO object
     *
     * @param employeeUCN
     * @param baseSalary
     * @param grossSalary
     * @param netSalary
     * @param dateOfPaycheck
     * @param hoursWorked
     * @param taxRate
     */
    public PaycheckDTO(final String employeeUCN, final double baseSalary, final double grossSalary,
            final double netSalary, final LocalDate dateOfPaycheck, final int hoursWorked,
            final double taxRate) {
        super();
        this.employeeUCN = employeeUCN;
        this.baseSalary = baseSalary;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
        this.dateOfPaycheck = dateOfPaycheck;
        this.hoursWorked = hoursWorked;
        this.taxRate = taxRate;
    }

    /**
     * @return Employee UCN of the paycheck
     */
    public String getEmployeeUCN() {
        return employeeUCN;
    }

    /**
     * @return Base salary
     *
     */
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * @return gross Salary
     */
    public double getGrossSalary() {
        return grossSalary;
    }

    /**
     * @return The net salary
     */
    public double getNetSalary() {
        return netSalary;
    }

    /**
     * @return the date of the Paycheck
     */
    public LocalDate getDateOfPaycheck() {
        return dateOfPaycheck;
    }

    /**
     * @return hours worked
     */
    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * @return taxRate
     */
    public double getTaxRate() {
        return taxRate;
    }

}
