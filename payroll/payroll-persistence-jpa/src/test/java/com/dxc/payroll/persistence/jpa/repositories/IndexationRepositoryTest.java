package com.dxc.payroll.persistence.jpa.repositories;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.common.Register;
import com.dxc.payroll.persistence.domain.Indexation;
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
 * Tests for the methods in indexation repository
 *
 */
@SuppressWarnings("nls")
public class IndexationRepositoryTest extends UnitilsJUnit4 {

    /**
     * Clean data base file path
     */
    private static final String CLEAN_DB_XML = "CleanDB.xml";

    /**
     * Entity manager factory - private field
     */
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
     * Transaction handler - private field
     */
    private final TransactionHandler transactionHandler = new TransactionHandlerImpl(
            entityManagerFactory, register);

    /**
     * Test that checks if the createIndexation method from IndexationRepository
     * works properly
     */
    @Test
    @DataSet({ "indexation/CreateForIndexation.xml", CLEAN_DB_XML })
    @ExpectedDataSet("indexation/CreateIndexationTest-result.xml")
    public void testCreateIndexation() {
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        final LocalDate dateOfIndexation = LocalDate.parse("2009-11-26", formatter);
        Assert.assertNotNull("The created job and position must not be null.",
                transactionHandler.execute((final Factory factory) -> {
                    final JobRepository jobRepository = factory.findService(JobRepository.class);
                    final Position position = jobRepository.findPositionBy("Developer", "Senior",
                            1);
                    final IndexationRepository indexationRepository = factory
                            .findService(IndexationRepository.class);
                    return indexationRepository.createIndexation(position, 1.75, dateOfIndexation);
                }));
    }

    /**
     * Test that checks if findIndexationBy from IndexationRepository method
     * works properly when there is indexation with the given job title
     */
    @Test
    @DataSet({ "indexation/FindIndexationsByJobTitle.xml", CLEAN_DB_XML })
    public void testFindIndexationByJobTitle() {
        final List<Indexation> indexations = transactionHandler
                .execute((final Factory factory) -> {
                    final IndexationRepository indexationRepository = factory
                            .findService(IndexationRepository.class);
                    final List<Indexation> indexationTest = indexationRepository
                            .findIndexationBy("Developer");
                    return indexationTest;
                });
        final int sizeOfList = 4;
        assertEquals("Invalid number of jobs", sizeOfList, indexations.size());
        assertPropertyLenientEquals("position.job.jobId.jobTitle",
                Collections.nCopies(sizeOfList, "Developer"), indexations);
    }

    /**
     * Test that checks if findIndexationBy from IndexationRepository method
     * works properly when there are no indexations with the given job title
     */
    @Test
    @DataSet({ "indexation/FindIndexationsByJobTitle.xml", CLEAN_DB_XML })
    public void testFindIndexationByJobTitleNoResult() {
        final List<Indexation> indexations = transactionHandler
                .execute((final Factory factory) -> {
                    final IndexationRepository indexationRepository = factory
                            .findService(IndexationRepository.class);
                    final List<Indexation> indexationTest = indexationRepository
                            .findIndexationBy("Manager");
                    return indexationTest;
                });
        assertEquals("Error! the expected result is 0! ", 0, indexations.size());
    }

    /**
     * Test that checks if the getIndexations method from the
     * IndexationRepository works properly where there are indexations in the
     * database
     */
    @Test
    @DataSet({ "indexation/FindIndexationsByJobTitle.xml", CLEAN_DB_XML })
    public void testGetIndexations() {
        final List<Indexation> indexations = transactionHandler
                .execute((final Factory factory) -> {
                    final IndexationRepository indexationRepository = factory
                            .findService(IndexationRepository.class);
                    final List<Indexation> indexationsTest = indexationRepository.getIndexations();
                    return indexationsTest;
                });
        final int listSize = 4;
        assertEquals("Invalid number of indexations", listSize, indexations.size());

    }

    /**
     * Test that checks if the getIndexations method from the
     * IndexationRepository works properly where there are no indexations in the
     * database
     */
    @Test
    @DataSet({ CLEAN_DB_XML })
    public void testGetIndexationsNoResult() {
        final List<Indexation> indexations = transactionHandler
                .execute((final Factory factory) -> {
                    final IndexationRepository indexationRepository = factory
                            .findService(IndexationRepository.class);
                    final List<Indexation> indexationsTest = indexationRepository.getIndexations();
                    return indexationsTest;
                });
        assertEquals("Error! the expected result is 0!", 0, indexations.size());
    }
}
