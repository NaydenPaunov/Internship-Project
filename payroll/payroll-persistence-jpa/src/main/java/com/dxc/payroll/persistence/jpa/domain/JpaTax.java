package com.dxc.payroll.persistence.jpa.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.dxc.payroll.persistence.domain.Tax;

/**
 * Entity for the tax
 */
@Entity
@IdClass(value = TaxID.class)
@NamedQueries({
        @NamedQuery(name = "qryGetTaxHistory", query = "select tax from JpaTax tax where" + 
" tax.typeOfTax = :typeOfTax"),
        @NamedQuery(name = "qryFindLastTaxByName", query = "select tax from JpaTax tax where" + 
" tax.typeOfTax = :typeOfTax and tax.endDate is null"),
        @NamedQuery(name = "qryGetAllCurrentTaxes", query = "select tax from JpaTax tax where" + 
" tax.endDate is null")
})
@Table(name = "TAX")
public class JpaTax implements Tax {
    /**
     * private field - the type of the tax
     */
    @Id
    @Column(name = "TYPE_OF_TAX")
    private String typeOfTax;

    /**
     * private field - the start date of the tax
     */
    @Id
    @Column(name = "START_DATE")
    private LocalDate startDate;

    /**
     * private field - the end date of the tax
     */
    @Column(name = "END_DATE")
    private LocalDate endDate;

    /**
     * private field - the percentage for the employee
     */
    @Column(name = "PERCENTAGE_EMPLOYEE")
    private double percentageEmployee;

    /**
     * private field - the percentage for the company
     */
    @Column(name = "PERCENTAGE_COMPANY")
    private double percentageCompany;

    /**
     * Needed by JPA
     */
    protected JpaTax() {
    }

    /**
     * Constructor for the tax entity
     *
     * @param typeOfTax
     *            the type of the tax, mustn't be null
     * @param startDate
     *            the start date of the tax, mustn't be null
     * @param endDate
     *            the end date of the tax
     * @param percentageE
     *            the employee percentage
     * @param percentageC
     *            the company percentage
     */
    public JpaTax(final String typeOfTax, final LocalDate startDate, final LocalDate endDate,
            final double percentageE, final double percentageC) {
        assert typeOfTax != null && startDate != null;
        this.typeOfTax = typeOfTax;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentageEmployee = percentageE;
        this.percentageCompany = percentageC;
    }

    /**
     * getter for type of tax
     */
    @Override
    public String getTypeOfTax() {
        return typeOfTax.trim();
    }

    /**
     * getter for tax start date
     */
    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * getter for tax end date
     */
    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * getter for tax employee percentage
     */
    @Override
    public double getPercentageEmployee() {
        return percentageEmployee;
    }

    /**
     * getter for tax company percentage
     */
    @Override
    public double getPercentageCompany() {
        return percentageCompany;
    }

    /**
     * setter for the tax end date, needed when we want to add new tax, and
     * close the old one
     */
    @Override
    public boolean closeTax(final LocalDate eDate) {
        if (eDate == null || endDate != null) {
            return false;
        }
        endDate = eDate;
        return true;
    }

}
