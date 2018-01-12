package com.dxc.payroll.persistence.jpa.repositories;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import com.dxc.payroll.common.Register;
import com.dxc.payroll.common.Factory;
import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.jpa.utils.TransactionHandlerImpl;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.IndexationRepository;
import com.dxc.payroll.persistence.repositories.JobRepository;
import com.dxc.payroll.persistence.repositories.PositionPeriodRepository;
import com.dxc.payroll.persistence.repositories.RoleRepository;
import com.dxc.payroll.persistence.repositories.TaxRepository;
import com.dxc.payroll.persistence.repositories.UserRightRepository;
import com.dxc.payroll.persistence.utils.TransactionHandler;

/**
 * Position period tests.
 */
@SuppressWarnings("nls")
public class PositionPeriodTest extends UnitilsJUnit4 {

    private final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("payroll");

    private static Register<EntityManager> register = registerRepositories();

    /**
     * Register repositories.
     *
     * @return Register
     */
    private static Register<EntityManager> registerRepositories() {
        final Register<EntityManager> repositoryRegister = new Register<>();
        repositoryRegister.registerService(EmployeeRepository.class, JpaEmployeeRepository::new);
        repositoryRegister.registerService(IndexationRepository.class,
                JpaIndexationRepository::new);
        repositoryRegister.registerService(JobRepository.class, JpaJobRepository::new);
        repositoryRegister.registerService(PositionPeriodRepository.class,
                JpaPositionPeriodRepository::new);
        repositoryRegister.registerService(TaxRepository.class, JpaTaxRepository::new);
        repositoryRegister.registerService(RoleRepository.class, JpaRoleRepository::new);
        repositoryRegister.registerService(UserRightRepository.class, JpaUserRightRepository::new);
        return repositoryRegister;
    }

    /**
     * private field - transaction handler
     */
    private final TransactionHandler transactionHandler = new TransactionHandlerImpl(
            entityManagerFactory, register);

    /**
     * The method tests if the createPositionInput method works correctly
     */
    @Test
    @DataSet({ "positionPeriod/CreateForPositionPeriod.xml", "CleanDB.xml" })
    @ExpectedDataSet("positionPeriod/PositionPeriodTest-result.xml")
    public void testCreatePositionInput() {
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        final LocalDate startDate = LocalDate.parse("2002-01-01", formatter);
        Assert.assertNotNull("The created position input must not be null.",
                transactionHandler.execute((final Factory factory) -> {
                    final EmployeeRepository employeeRepo = factory
                            .findService(EmployeeRepository.class);
                    final Employee employee = employeeRepo.findByUCN("0123456789");
                    final JobRepository jobRepository = factory.findService(JobRepository.class);
                    final Position position = jobRepository.findPositionBy("Developer", "Junior",
                            4);
                    final PositionPeriodRepository positionPeriodRepository = factory
                            .findService(PositionPeriodRepository.class);
                    return positionPeriodRepository.createPositionInput(startDate, 1900.00,
                            position, employee);
                }));
    }

}
