package com.dxc.payroll.service.exceptions;

/**
 * Employee not found exception.
 */
public class EmployeeNotFoundException extends PayrollException {
    /**
     * Exception for the tax
     */
    private static final long serialVersionUID = -8968954741789952419L;

    /**
     * Employee not found exception.
     * 
     * @param errorMessage
     */
    public EmployeeNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
