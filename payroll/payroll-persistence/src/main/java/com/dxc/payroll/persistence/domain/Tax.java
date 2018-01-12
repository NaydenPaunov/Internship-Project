package com.dxc.payroll.persistence.domain;

import java.time.LocalDate;
/**
 * Interface for the Tax entity 
 */
public interface Tax {
    /**
     * getter for the type of tax
     */
    String getTypeOfTax();

    /**
     * getter for the start date
     */
    LocalDate getStartDate();

    /**
     * getter for the end date
     */
    LocalDate getEndDate();

    /**
     * getter for the employee percentage
     */
    double getPercentageEmployee();

    /**
     * getter for the company percentage
     */
    double getPercentageCompany();
    
    /**
     * setter for the end date, the current end date must be null
     * @param endDate the end date for the tax, mustn't be null
     */
    boolean closeTax(LocalDate endDate);
    
}
