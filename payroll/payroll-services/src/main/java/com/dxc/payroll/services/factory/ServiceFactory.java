package com.dxc.payroll.services.factory;

import com.dxc.payroll.services.EmployeeService;
import com.dxc.payroll.services.IndexationService;
import com.dxc.payroll.services.JobService;
import com.dxc.payroll.services.PaycheckService;
import com.dxc.payroll.services.PositionHistoryService;
import com.dxc.payroll.services.PositionService;
import com.dxc.payroll.services.RightsService;
import com.dxc.payroll.services.TaxService;

/**
 * Interface for Service Factory
 * 
 * @author dtanev
 *
 */
public interface ServiceFactory {

    /**
     * Getter for Employee service
     * 
     * @return EmployeeService object
     */
    EmployeeService getEmployeeService();

    /**
     * Getter for Indexation Service
     * 
     * @return IndexationService object
     */
    IndexationService getIndexationService();

    /**
     * Getter for Job Service
     * 
     * @return JobService object
     */
    JobService getJobService();

    /**
     * Getter for Paycheck Service
     * 
     * @return PaycheckService object
     */
    PaycheckService getPaycheckService();

    /**
     * Getter for Position History Service
     * 
     * @return PositionHistoryService object
     */
    PositionHistoryService getPositionHistoryService();

    /**
     * Getter for Position Service
     * 
     * @return PositionService object
     */
    PositionService getPositionService();

    /**
     * Getter for Rights Service
     * 
     * @return RightsService object
     */
    RightsService getRightsService();

    /**
     * Getter for Tax Service
     *
     * @return TaxService object
     */
    TaxService getTaxService();
}
