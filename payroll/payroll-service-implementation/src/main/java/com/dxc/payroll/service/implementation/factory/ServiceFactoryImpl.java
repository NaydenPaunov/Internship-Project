package com.dxc.payroll.service.implementation.factory;

import com.dxc.payroll.persistence.utils.TransactionHandler;
import com.dxc.payroll.service.implementation.EmployeeServiceImpl;
import com.dxc.payroll.service.implementation.IndexationServiceImpl;
import com.dxc.payroll.service.implementation.JobServiceImpl;
import com.dxc.payroll.service.implementation.PaycheckServiceImpl;
import com.dxc.payroll.service.implementation.PositionHistoryServiceImpl;
import com.dxc.payroll.service.implementation.PositionServiceImpl;
import com.dxc.payroll.service.implementation.RightsServiceImpl;
import com.dxc.payroll.service.implementation.TaxServiceImpl;
import com.dxc.payroll.services.EmployeeService;
import com.dxc.payroll.services.IndexationService;
import com.dxc.payroll.services.JobService;
import com.dxc.payroll.services.PaycheckService;
import com.dxc.payroll.services.PositionHistoryService;
import com.dxc.payroll.services.PositionService;
import com.dxc.payroll.services.RightsService;
import com.dxc.payroll.services.TaxService;
import com.dxc.payroll.services.factory.ServiceFactory;

/**
 * Service Factory - Implemettatiton
 *
 * @author dtanev
 *
 */
public class ServiceFactoryImpl implements ServiceFactory {

    private final TransactionHandler transactionHandler;

    public ServiceFactoryImpl(final TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    /**
     * Getter for Employee service
     *
     * @return EmployeeService object
     */
    @Override
    public EmployeeService getEmployeeService() {
        return new EmployeeServiceImpl(transactionHandler);
    }

    /**
     * Getter for Indexation Service
     *
     * @return IndexationService object
     */
    @Override
    public IndexationService getIndexationService() {
        return new IndexationServiceImpl();
    }

    /**
     * Getter for Job Service
     *
     * @return JobService object
     */
    @Override
    public JobService getJobService() {
        return new JobServiceImpl(transactionHandler);
    }

    /**
     * Getter for Paycheck Service
     *
     * @return PaycheckService object
     */
    @Override
    public PaycheckService getPaycheckService() {
        return new PaycheckServiceImpl(transactionHandler);
    }

    /**
     * Getter for Position History Service
     *
     * @return PositionHistoryService object
     */
    @Override
    public PositionHistoryService getPositionHistoryService() {
        return new PositionHistoryServiceImpl();
    }

    /**
     * Getter for Position Service
     *
     * @return PositionService object
     */
    @Override
    public PositionService getPositionService() {
        return new PositionServiceImpl(transactionHandler);
    }

    /**
     * Getter for Rights Service
     *
     * @return RightsService object
     */
    @Override
    public RightsService getRightsService() {
        return new RightsServiceImpl();
    }

    /**
     * Getter for Tax Service
     *
     * @return TaxService object
     */
    @Override
    public TaxService getTaxService() {
        return new TaxServiceImpl(transactionHandler);
    }

}
