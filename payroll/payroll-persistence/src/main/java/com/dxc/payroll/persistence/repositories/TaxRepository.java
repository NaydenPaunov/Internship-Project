package com.dxc.payroll.persistence.repositories;

import java.time.LocalDate;
import java.util.List;

import com.dxc.payroll.persistence.domain.Tax;

/**
 *
 * Repository for the tax
 *
 */
public interface TaxRepository {
    /**
     * Creates tax in the database with the given parameters
     *
     * @param taypeOfTax
     *            the type of the tax, mustn't be null
     * @param startdate
     *            the start date of the tax, mustn't be null
     * @param endDate
     *            the end date of the tax
     * @param percentageEmployee
     *            the percentage for the employee
     * @param percentageCompany
     *            the percentage for the company
     * @return the tax created
     */
    Tax createTax(String typeOfTax, LocalDate startDate, LocalDate endDate,
            double percentageEmployee, double percentageCompany);

    /**
     * Finds tax with the given startDate and type of tax
     *
     * @param startDate
     * @param typeOfTax
     * @return the tax found, if there is no found tax the method returns null
     */
    Tax findTaxByNameAndDate(LocalDate startDate, String typeOfTax);

    /**
     * Gives list of taxes with the given name and null end date
     *
     * @param typeOfTax
     *            mustn't be null
     * @return method returns list of taxes, if there is no found taxes with
     *         this name the method returns empty list
     */
    List<Tax> findUnclosedTaxesWithGivenName(String typeOfTax);

    /**
     * Finds taxes with null end date
     *
     * @return list of all current taxes
     */
    List<Tax> getAllCurrentTaxes();

    /**
     * The method gives list of all previous taxes with this name and the
     * current tax
     *
     * @param typeOfTax
     *            the type of the tax you are searching for
     * @return list of taxes with the given name
     */
    List<Tax> getTaxHistory(String typeOfTax);

    /**
     * Method closes the given tax
     *
     * @param taxToClose
     *            - the tax you want to close, must not be null
     * @param endDate
     *            - the end date you want to set
     * @return true if the current end date of the tax is null, false if the tax
     *         is already closed
     */
    boolean closeTax(Tax taxToClose, LocalDate endDate);

}
