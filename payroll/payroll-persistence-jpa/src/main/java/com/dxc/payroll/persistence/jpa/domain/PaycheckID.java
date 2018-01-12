package com.dxc.payroll.persistence.jpa.domain;

import static java.util.Objects.hash;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;

/**
 * Composite primary key for Paycheck entity.
 *
 */
@Embeddable
public class PaycheckID implements Serializable {

    /**
     * Generated serial version ID for PaycheckID.
     */
    private static final long serialVersionUID = 2685876252830796373L;

    /**
     * UCN of the employee.
     */
    private String employee;

    /**
     * Date of the paycheck.
     */
    private LocalDate date;

    /**
     * Used by JPA.
     */
    public PaycheckID() {
        // Required by JPA.
    }

    /**
     * @param employeeUCN
     *            UCN of the employee, must not be null.
     * @param paycheckDate
     *            The Date of the paycheck, must not be null.
     */
    public PaycheckID(final String employeeUCN, final LocalDate paycheckDate) {
        employee = employeeUCN;
        date = paycheckDate;
    }

    /**
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof PaycheckID)) {
            return false;
        }
        final PaycheckID paycheckID = (PaycheckID) other;
        return paycheckID.employee.equals(employee) && paycheckID.date.equals(date);
    }

    /**
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return hash(employee, date);
    }
}
