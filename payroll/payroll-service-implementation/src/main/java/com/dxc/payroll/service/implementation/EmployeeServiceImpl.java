package com.dxc.payroll.service.implementation;

import java.util.List;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.TaxRepository;
import com.dxc.payroll.persistence.utils.TransactionHandler;
import com.dxc.payroll.service.exceptions.EmployeeNotFoundException;
import com.dxc.payroll.service.validation.Type;
import com.dxc.payroll.services.EmployeeService;
import com.dxc.payroll.services.dto.EmployeeDTO;

/**
 * Implementation of EmployeeService.
 */
public class EmployeeServiceImpl extends AbstractService implements EmployeeService {
    /**
     * Employee service implementation constructor.
     *
     * @param transactionHandler
     *            To be used for transactions. Must not be null.
     */
    public EmployeeServiceImpl(final TransactionHandler transactionHandler) {
        super(transactionHandler);
    }

    /**
     * Find employee.
     *
     * @throws EmployeeNotFoundException
     *             - If employee with this ucn does not exist. If
     */
    @Override
    public EmployeeDTO findEmployee(final String ucn) {
        validateParameter(ucn);
        return transactionHandler.execute((final Factory factory) -> EmployeeServiceLogic
                .findEmployee(factory.findService(EmployeeRepository.class),
                        factory.findService(TaxRepository.class), ucn));
    }

    /**
     * Find subordinate for this team lead.
     *
     * If there are no subordinates for this team lead, an empty list is
     * returned.
     */
    @Override
    public List<EmployeeDTO> findEmployeesByTeamLeadUCN(final String ucn) {
        validateParameter(ucn);
        return transactionHandler.execute((final Factory factory) -> EmployeeServiceLogic
                .findEmployeeByTeamLeadUCN(factory.findService(EmployeeRepository.class),
                        factory.findService(TaxRepository.class), ucn));
    }

    /**
     * Get list of all employees.
     *
     * If there are no employees in the database, an empty list is returned.
     */
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return transactionHandler.execute((final Factory factory) -> EmployeeServiceLogic
                .getAllEmployees(factory.findService(EmployeeRepository.class),
                        factory.findService(TaxRepository.class)));
    }

    /**
     * Validate ucn parameter.
     *
     * @param ucn
     */
    private void validateParameter(final String ucn) {
        this.validator.addForValidation(Type.UCN, ucn);
        this.validator.validateAll();
    }

}
