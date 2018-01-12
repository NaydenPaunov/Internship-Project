package com.dxc.payroll.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.persistence.domain.Tax;
import com.dxc.payroll.persistence.repositories.TaxRepository;
import com.dxc.payroll.persistence.utils.TransactionHandler;
import com.dxc.payroll.service.exceptions.TaxException;
import com.dxc.payroll.service.exceptions.TaxNotFoundException;
import com.dxc.payroll.services.TaxService;
import com.dxc.payroll.services.dto.TaxDTO;

/**
 * Implements TaxService
 *
 */

public class TaxServiceImpl implements TaxService {
    /**
     * private field transactionHandler
     */
    private final TransactionHandler transactionHandler;

    /**
     * Constructor for TaxServiceImpl
     *
     * @param transactionHandler
     */
    public TaxServiceImpl(final TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    /**
     * @see com.dxc.payroll.services.TaxService
     */
    @SuppressWarnings("nls")
    @Override
    public TaxDTO findTaxByNameAndDate(final LocalDate startDate, final String typeOfTax) {
        return transactionHandler.execute((final Factory factory) -> {
            final TaxRepository taxRepository = factory.findService(TaxRepository.class);
            final Tax tax = taxRepository.findTaxByNameAndDate(startDate, typeOfTax);
            if (tax == null) {
                throw new TaxNotFoundException("No such tax", typeOfTax, startDate);
            }
            return toTaxDTO(tax);
        });
    }

    private static TaxDTO toTaxDTO(final Tax tax) {
        return new TaxDTO(tax.getStartDate(), tax.getEndDate(), tax.getPercentageEmployee(),
                tax.getPercentageCompany(),
                tax.getTypeOfTax());

    }

    /**
     * @see com.dxc.payroll.services.TaxService
     */
    @SuppressWarnings("nls")
    @Override
    public List<TaxDTO> updateTaxesWithGivenNames(final Map<String, List<Double>> taxesToUpdate) {
        return transactionHandler.execute((final Factory factory) -> {
            final TaxRepository taxRepository = factory.findService(TaxRepository.class);
            final List<TaxDTO> taxesCreated = new ArrayList<>();
            for (final Entry<String, List<Double>> entry : taxesToUpdate.entrySet()) {
                final List<Tax> unclosedTaxes = taxRepository
                        .findUnclosedTaxesWithGivenName(entry.getKey());
                if (unclosedTaxes.size() != 1) {
                    throw new TaxException(
                            "There is more or less than one taxes with null end date.",
                            entry.getKey(), unclosedTaxes.size());
                }
                taxRepository.closeTax(unclosedTaxes.get(0), LocalDate.now());
                final Tax taxCreated = taxRepository.createTax(entry.getKey(), LocalDate.now(),
                        null,
                        entry.getValue().get(0).doubleValue(),
                        entry.getValue().get(1).doubleValue());
                taxesCreated.add(toTaxDTO(taxCreated));
            }
            return taxesCreated;
        });
    }

    /**
     * @see com.dxc.payroll.services.TaxService
     */
    @Override
    public List<TaxDTO> getAllCurrentTaxes() {
        return transactionHandler.execute((final Factory factory) -> {
            final TaxRepository taxRepository = factory.findService(TaxRepository.class);
            final List<Tax> taxes = taxRepository.getAllCurrentTaxes();
            final List<TaxDTO> taxesDTO = new LinkedList<>();
            for (final Tax tax : taxes) {
                taxesDTO.add(toTaxDTO(tax));
            }
            return taxesDTO;
        });
    }

    /**
     * @see com.dxc.payroll.services.TaxService
     */
    @Override
    public List<TaxDTO> getTaxHistory(final String typeOfTax) {
        return transactionHandler.execute((final Factory factory) -> {
            final TaxRepository taxRepository = factory.findService(TaxRepository.class);
            final List<Tax> taxes = taxRepository.getTaxHistory(typeOfTax);
            final List<TaxDTO> taxesDTO = new LinkedList<>();
            for (final Tax tax : taxes) {
                taxesDTO.add(toTaxDTO(tax));
            }
            return taxesDTO;
        });
    }
}
