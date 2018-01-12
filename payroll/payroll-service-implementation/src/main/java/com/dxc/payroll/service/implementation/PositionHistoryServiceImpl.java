package com.dxc.payroll.service.implementation;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.utils.TransactionHandler;
import com.dxc.payroll.service.exceptions.EmptyValueException;
import com.dxc.payroll.service.validation.Type;
import com.dxc.payroll.services.PositionHistoryService;
import com.dxc.payroll.services.dto.PositionHistoryForEmployeeDTO;

/**
 * Implementation for Position History Service.
 *
 * The class has one method findPositionHistory(), that receives a UCN and
 * return PositionHistoryForEmployeeDTO.
 *
 * Extends AbstractService class.
 *
 */
public final class PositionHistoryServiceImpl extends AbstractService
        implements PositionHistoryService {
    /**
     * Constant for global logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Constructor for PositionHistoryServiceImpl
     *
     * @param transactionHandler
     *            of type TransactionHandler. Must not be null.
     */
    public PositionHistoryServiceImpl(final TransactionHandler transactionHandler) {
        super(transactionHandler);
    }

    /**
     * Finding the position history for an employee. The method receives a
     * string UCN. Checks whether there is positions with this UCN. If exist get
     * all position history for the employee and return
     * PositionHistoryForEmployeeDTO, else return null.
     *
     * @param employeeUCN
     *            of type String
     *
     * @return List of Position History DTO objects.
     *
     * @throws EmptyValueException
     */
    @SuppressWarnings("nls")
    @Override
    public PositionHistoryForEmployeeDTO findPositionHistory(final String employeeUCN) {
        if (employeeUCN == null || employeeUCN.isEmpty()) {
            final String exceptionMessage = "employeeUCN has not been entered.";
            LOGGER.log(Level.WARNING, exceptionMessage);
            throw new EmptyValueException(exceptionMessage);
        }
        validator.addForValidation(Type.UCN, employeeUCN);
        validator.validateAll();
        return transactionHandler.execute((final Factory factory) -> PositionHistoryServiceLogic
                .findPositionHistory(employeeUCN, factory.findService(EmployeeRepository.class)));
    }

}
