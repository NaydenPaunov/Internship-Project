package com.dxc.payroll.persistence.repositories;

/**
 * Create repositories.
 *
 */
public interface RepositoryFactory {
    /**
     * Create employee repository
     * 
     * @return EmployeeRepository
     */
    EmployeeRepository createEmployeeRepository();

    /**
     * Create position history repository.
     * 
     * @return PositionHistoryRepository
     */
    PositionHistoryRepository createPositionHistoryRepository();

    /**
     * Create job repository.
     * 
     * @return JobRepository
     */
    JobRepository createJobRepository();

    /**
     * Create indexation repository.
     * 
     * @return IndexationRepository
     */
    IndexationRepository createIndexationRepository();

    /**
     * Create tax repository.
     * 
     * @return TaxRepository
     */
    TaxRepository createTaxRepository();
    
    /**
     * Create position repository
     * 
     * @return PositionRepository
     */
    PositionRepository createPositionRepository();
}