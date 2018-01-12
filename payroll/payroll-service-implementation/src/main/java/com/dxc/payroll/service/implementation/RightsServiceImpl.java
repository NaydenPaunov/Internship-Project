package com.dxc.payroll.service.implementation;

import java.util.List;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.UserRightRepository;
import com.dxc.payroll.persistence.utils.TransactionHandler;
import com.dxc.payroll.services.RightsService;
import com.dxc.payroll.services.dto.RightsDTO;

/**
 * An implementation of the rights Service Implements a method to return a
 * RightsDTO by user UCN, and a method to fetch all the rights from the database
 */

public class RightsServiceImpl extends AbstractService implements RightsService {

    /**
     * @param transactionHandler
     */
    public RightsServiceImpl(final TransactionHandler transactionHandler) {
        super(transactionHandler);
    }

    /**
     * Returns a list of DTO objects that hold the rights of the employee with
     * the given ucn
     *
     * @param ucn
     *            the ucn of a user
     * @return a list of the rights the user with the given ucn has
     */
    @Override
    public List<RightsDTO> getRightsByUCN(final String ucn) {
        return transactionHandler.execute((final Factory factory) -> RightsServiceLogic
                .getRights(ucn, factory.findService(EmployeeRepository.class)));
    }

    /**
     * This method returns a list of DTOs that hold all the fields of a
     * UserRight object
     *
     * @return a list of DTO containing all the rights
     */
    @Override
    public List<RightsDTO> getAllRights() {
        return transactionHandler.execute((final Factory factory) -> RightsServiceLogic
                .getAllRights(factory.findService(UserRightRepository.class)));
    }

}
