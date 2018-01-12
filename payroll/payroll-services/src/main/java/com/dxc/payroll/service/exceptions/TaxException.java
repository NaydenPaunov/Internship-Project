package com.dxc.payroll.service.exceptions;

/**
 * @see com.dxc.payroll.service.exceptions.PayrollExceptions
 * 
 * Exception for more or less than one taxes with null end date with given name
 *
 */
public class TaxException extends PayrollException {
    /**
     * Exception for the tax
     */
    private static final long serialVersionUID = -8968954741789952219L;
    /**
     * the type of the tax that have more or less than one null end date
     */
    private final String typeOfTax;
    /**
     * the size of the list
     */
    private final int listLength;

    /**
     * Constructor for the tax exception
     *
     * @param errorMessage
     *            the exception message
     * @param typeOfTax
     *            the type of the tax
     * @param listLength
     *            the size of the list gives specific information about the type
     *            of the tax exception
     */
    public TaxException(final String errorMessage, final String typeOfTax, final int listLength) {
        super(errorMessage);
        this.typeOfTax = typeOfTax;
        this.listLength = listLength;

    }
    /**
     * getter for the length of the list 
     * @return list length 
     */
    public int getListLength() {
        return listLength;
    }
    
    /**
     * getter for the type of the tax with exception
     * @return type of tax
     */ 
    public String getTypeOfTax() {
        return typeOfTax;
    }

}
