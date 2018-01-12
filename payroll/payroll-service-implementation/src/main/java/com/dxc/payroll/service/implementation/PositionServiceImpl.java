package com.dxc.payroll.service.implementation;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.JobRepository;
import com.dxc.payroll.persistence.repositories.PositionPeriodRepository;
import com.dxc.payroll.persistence.utils.TransactionHandler;
import com.dxc.payroll.service.exceptions.EmptyValueException;
import com.dxc.payroll.services.PositionService;
import com.dxc.payroll.services.dto.EmployeePositionDTO;
import com.dxc.payroll.services.dto.RaiseAnEmployeeSimulationDTO;

/**
 *
 * Implementation of PositionService interface. The class PositionServiceImpl
 * extends the abstract class - Abstract service.
 *
 */
public class PositionServiceImpl extends AbstractService implements PositionService {
    /**
     *
     * Constructor for the JobServiceImpl
     *
     * @param transactionHandler
     *
     */
    public PositionServiceImpl(final TransactionHandler transactionHandler) {
        super(transactionHandler);
    }

    /**
     * @see com.dxc.payroll.services.PositionService#getInformationForRaiseAnEmployee
     *      (java.lang.String)
     */
    @Override
    public RaiseAnEmployeeSimulationDTO getInformationForRaiseAnEmployee(final String employeeUCN) {
        return transactionHandler.execute(
                (final Factory factory) -> PositionServiceLogic.getInformationForRaiseAnEmployee(
                        employeeUCN, factory.findService(JobRepository.class),
                        factory.findService(EmployeeRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.PositionService#raiseEmployee
     *      (java.lang.String),(java.lang.String)
     * 
     * @throws EmptyValueException
     */
    @Override
    public void raiseEmployee(final String employeeUCN, final String jobDegree,
            final int jobLevel) {
        transactionHandler
                .execute((final Factory factory) -> PositionServiceLogic.raiseEmployee(employeeUCN,
                        jobDegree, jobLevel, factory.findService(EmployeeRepository.class),
                        factory.findService(JobRepository.class),
                        factory.findService(PositionPeriodRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.PositionService#simulationRaising
     *      (java.lang.String),(java.lang.String),(java.lang.Integer)
     */
    @Override
    public RaiseAnEmployeeSimulationDTO simulationRaising(final String employeeUCN,
            final String jobDegree, final int jobLevel) {
        return transactionHandler.execute(
                (final Factory factory) -> PositionServiceLogic.simulationRaising(employeeUCN,
                        jobDegree, jobLevel, factory.findService(EmployeeRepository.class),
                        factory.findService(JobRepository.class)));
    }

    /**
     * @see com.dxc.payroll.services.PositionService#getPositionByEmployeeUCN
     *      (java.lang.String)
     */
    @Override
    public EmployeePositionDTO getPositionByEmployeeUCN(final String employeeUCN) {
        return transactionHandler
                .execute((final Factory factory) -> PositionServiceLogic.getPositionByEmployeeUCN(
                        employeeUCN, factory.findService(EmployeeRepository.class)));

    }
}
