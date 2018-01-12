package com.dxc.payroll.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.dxc.payroll.service.exceptions.TaxException;
import com.dxc.payroll.services.dto.TaxDTO;

/**
 * Service interface for the Tax
 *
 */
public interface TaxService {
    /**
     * Finds tax with the given name and startDate
     *
     * @param startDate
     *            the start date of the wanted tax, must not be null
     * @param typeOfTax
     *            the name of the wanted tax, must not be null
     * @return TaxDTO containing typeOfTax, start and end date, percentage for
     *         the employee and percentage for the company
     */
    TaxDTO findTaxByNameAndDate(LocalDate startDate, String typeOfTax);

    /**
     * The method closes current tax with the given name and creates a new one
     *
     * @param typeOfTax
     *            the name of the tax you want to update, must not be null
     * @param percentageEmployee
     *            the new percentage for the employee
     * @param percentageCompany
     *            the new percentage for the company
     * @return the new tax containing the given name and percentages, start date
     *         - current date and null end date
     * @throws TaxException
     *             if there is more or less than one taxes to update
     */
    List<TaxDTO> updateTaxesWithGivenNames(Map<String, List<Double>> taxesToUpdate);

    /**
     * The method gives list of current taxes
     *
     * @return list of TaxDTO objects with null end date
     */
    List<TaxDTO> getAllCurrentTaxes();

    /**
     * The method gives list of taxes with the given name
     *
     * @param the
     *            type of the tax
     * @return list of TaxDTO objects with given type of tax
     */
    List<TaxDTO> getTaxHistory(String typeOfTax);

}
