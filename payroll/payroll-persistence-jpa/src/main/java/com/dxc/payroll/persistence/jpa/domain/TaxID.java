package com.dxc.payroll.persistence.jpa.domain;

import java.io.Serializable;
import java.time.LocalDate;

import com.dxc.payroll.persistence.jpa.utils.Utils;

/**
 * Composite primary key for Tax entity.
 *
 */
public class TaxID implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * private field - the fixed size for the tax name
     */
    public static final int SIZE_TAX_NAME = 32;

    /**
     * private field - the name
     */
    private String typeOfTax;

    /**
     * private field - the start date
     */
    private LocalDate startDate;

    /**
     * Required by JPA
     */
    public TaxID() {
    }

    /**
     * @param typeOfTax
     *            - the name of the tax, mustn't be null
     * @param startDate
     *            - the start date of the tax, mustn't be null
     */
    public TaxID(final String typeOfTax, final LocalDate startDate) {
        this.typeOfTax = Utils.padRight(typeOfTax, SIZE_TAX_NAME);
        this.startDate = startDate;

    }

    /**
     * equals for Tax
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof TaxID)) {
            return false;
        }
        final TaxID taxKey = (TaxID) obj;
        return taxKey.typeOfTax.equals(typeOfTax) && taxKey.startDate.equals(startDate);
    }

    /**
     * hashCode for Tax
     */
    @Override
    public int hashCode() {
        return typeOfTax.hashCode() + startDate.hashCode();
    }

}
