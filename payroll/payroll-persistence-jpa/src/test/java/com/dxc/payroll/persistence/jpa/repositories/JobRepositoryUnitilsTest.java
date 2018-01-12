package com.dxc.payroll.persistence.jpa.repositories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import com.dxc.payroll.persistence.jpa.utils.TransactionHandlerImpl;
import com.dxc.payroll.persistence.repositories.JobRepository;
import com.dxc.payroll.persistence.repositories.RepositoryFactory;
import com.dxc.payroll.persistence.utils.TransactionHandler;

/**
 * @author milieva5
 *
 */
public class JobRepositoryUnitilsTest extends UnitilsJUnit4 {
    @SuppressWarnings("nls")
    private final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("payroll");
    private final TransactionHandler handler = new TransactionHandlerImpl(entityManagerFactory);

    /**
     *
     * Test for create job
     *
     * @throws Throwable
     */
    @SuppressWarnings("nls")
    @Test
    @DataSet("job/JobRepositoryTest.xml")
    @ExpectedDataSet("job/JobRepositoryTest-result.xml")
    public void testCreateJob() throws Throwable {
        handler.execute((final RepositoryFactory factory) -> {
            final JobRepository jobRepository = factory.createJobRepository();
            return jobRepository.createJob("Junior Java Developer", 1200, 2300);
        });
    }
}