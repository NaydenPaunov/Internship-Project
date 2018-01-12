package com.dxc.payroll.service.implementation;

import java.time.LocalDate;
import java.util.List;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.TaxRepository;
import com.dxc.payroll.persistence.utils.TransactionHandler;
import com.dxc.payroll.services.PaycheckService;
import com.dxc.payroll.services.dto.PaycheckDTO;

/**
 * Implementation of Paycheck Service.
 */
public class PaycheckServiceImpl extends AbstractService implements PaycheckService {

    /**
     * Constructor for PaycheckServiceImpl.
     *
     * @param transactionHandler
     *            To be used for transactions. Must not be null.
     */
    public PaycheckServiceImpl(final TransactionHandler transactionHandler) {
        super(transactionHandler);
    }

    /**
     * Gets the employee paychecks by UCN.
     *
     * @param ucn
     *            UCN of the employee
     * @return list of paychecks, associated with the employee, null if an
     *         employee is not found, empty list of paychecks if the employee
     *         does not have paychecks
     */
    @Override
    public List<PaycheckDTO> getEmployeePaychecksByUCN(final String ucn) {
        return transactionHandler.execute((final Factory factory) -> {
            final EmployeeRepository employeeRepository = factory
                    .findService(EmployeeRepository.class);
            return PaycheckServiceLogic.getEmployeePaychecksByUCN(ucn, employeeRepository);
        });
    }

    /**
     * Creates PaycheckDTO with the given parameters
     */
    @Override
    public PaycheckDTO generatePaycheck(final String ucn, final LocalDate dateOfPaycheck,
            final int hoursWorked) {
        return transactionHandler
                .execute((final Factory factory) -> PaycheckServiceLogic.generatePaycheck(ucn,
                        dateOfPaycheck, hoursWorked, factory.findService(EmployeeRepository.class),
                        factory.findService(TaxRepository.class)));

    }

    /**
     * Saves the new paycheck in the database using the PaycheckServiceLogic
     * method - savePaycheck
     */
    @Override
    public PaycheckDTO savePaycheck(final String ucn, final LocalDate date, final int hoursWorked,
            final double grossSalary, final double taxRate, final double netSalary,
            final double baseSalary) {
        return transactionHandler
                .execute((final Factory factory) -> PaycheckServiceLogic.savePaycheck(ucn, date,
                        hoursWorked, grossSalary, taxRate, netSalary, baseSalary, factory));
    }
}
