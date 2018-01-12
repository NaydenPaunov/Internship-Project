package com.dxc.payroll.service.exceptions;

/**
 * Common exception
 */
public class PayrollException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for the PayrollException
     *
     * @param errorMessage
     *            the message of the error
     */
    public PayrollException(final String errorMessage) {
        super(errorMessage);
    }

}
