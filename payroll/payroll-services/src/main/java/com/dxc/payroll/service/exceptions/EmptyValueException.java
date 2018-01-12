package com.dxc.payroll.service.exceptions;

/**
 * Exception for using empty value.
 */
public class EmptyValueException extends PayrollException {
    /**
     * serial version
     */
    private static final long serialVersionUID = -6202921711446955389L;

    /**
     * Empty value exception constructor
     *
     * @param errorMessage
     */
    public EmptyValueException(final String errorMessage) {
        super(errorMessage);
    }

}
