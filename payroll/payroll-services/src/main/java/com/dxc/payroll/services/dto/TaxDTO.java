package com.dxc.payroll.services.dto;

import java.time.LocalDate;

/**
 * DTO class for the Tax
 *
 */
public class TaxDTO {
    /**
     * private field - the name
     */
    private final String typeOfTax;

    /**
     * private field - start date
     */
    private final LocalDate startDate;

    /**
     * private field - end date
     */
    private final LocalDate endDate;

    /**
     * private field - percentage for the employee
     */
    private final double percentageEmployee;

    /**
     * private field - percentage for the company
     */
    private final double percentageCompany;

    /**
     * Constructor for TaxDTO
     * 
     * @param startDate
     * @param endDate
     * @param percentageEmployee
     * @param percentageCompany
     * @param typeOfTax
     */
    public TaxDTO(final LocalDate startDate, final LocalDate endDate,
            final double percentageEmployee, final double percentageCompany,
            final String typeOfTax) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentageEmployee = percentageEmployee;
        this.percentageCompany = percentageCompany;
        this.typeOfTax = typeOfTax;
    }

    /**
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     *
     * @return the percentage for the company
     */
    public double getPercentageCompany() {
        return percentageCompany;
    }

    /**
     *
     * @return the percentage for the employee
     */
    public double getPercentageEmployee() {
        return percentageEmployee;
    }

    /**
     *
     * @return the name of the tax
     */
    public String getTypeOfTax() {
        return typeOfTax;
    }
}
