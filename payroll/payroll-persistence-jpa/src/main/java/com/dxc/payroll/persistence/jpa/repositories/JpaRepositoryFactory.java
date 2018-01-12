package com.dxc.payroll.persistence.jpa.repositories;

import javax.persistence.EntityManager;

import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.IndexationRepository;
import com.dxc.payroll.persistence.repositories.JobRepository;
import com.dxc.payroll.persistence.repositories.PositionHistoryRepository;
import com.dxc.payroll.persistence.repositories.PositionRepository;
import com.dxc.payroll.persistence.repositories.RepositoryFactory;
import com.dxc.payroll.persistence.repositories.TaxRepository;

/**
 * @author sshishkov
 *
 */
public final class JpaRepositoryFactory implements RepositoryFactory {
    private final EntityManager entityManager;

    /**
     * @param entityManager
     */
    public JpaRepositoryFactory(final EntityManager entityManager) {
        assert entityManager != null;
        this.entityManager = entityManager;
    }

    @Override
    public EmployeeRepository createEmployeeRepository() {
        return new JpaEmployeeRepository(entityManager);
    }

    @Override
    public PositionHistoryRepository createPositionHistoryRepository() {
        return new JpaPositionHistoryRepository(entityManager);
    }

    @Override
    public JobRepository createJobRepository() {
        return new JpaJobRepository(entityManager);
    }

    @Override
    public IndexationRepository createIndexationRepository() {
        return new JpaIndexationRepository(entityManager);
    }
    
    @Override
    public TaxRepository createTaxRepository() {
        return new JpaTaxRepository(entityManager);

    }

	@Override
	public PositionRepository createPositionRepository() {
		return new JpaPositionRepository(entityManager);
	}

	

}
