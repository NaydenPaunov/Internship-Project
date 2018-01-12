package com.dxc.payroll.service.exceptions;

import java.time.LocalDate;

/**
 *
 * @see com.dxc.payroll.service.exceptions.PayrollExceptions
 *
 *      Exception for not found tax with given name and start date
 *
 */
public class TaxNotFoundException extends PayrollException {

    /**
     * private field serialVersionUID
     */
    private static final long serialVersionUID = -530444649865846258L;

    /**
     * the type of the tax that is missing
     */
    private final String typeOfTax;

    /**
     * the start date of the tax
     */
    private final LocalDate startDate;

    /**
     * Constructor for the exception
     *
     * @param errorMessage
     *            message for the exception
     * @param typeOfTax
     * @param startDate
     */
    public TaxNotFoundException(final String errorMessage, final String typeOfTax,
            final LocalDate startDate) {
        super(errorMessage);
        this.typeOfTax = typeOfTax;
        this.startDate = startDate;
    }
    
    /**
     * getter for the start date
     * @return start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    
    /**
     * getter for the type of tax
     * @return type of tax
     */
    public String getTypeOfTax() {
        return typeOfTax;
    }

}
