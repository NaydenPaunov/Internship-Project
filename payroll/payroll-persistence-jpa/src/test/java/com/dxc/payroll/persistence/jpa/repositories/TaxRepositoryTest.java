package com.dxc.payroll.persistence.jpa.repositories;

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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

import com.dxc.payroll.common.Register;
import com.dxc.payroll.common.Factory;
import com.dxc.payroll.persistence.domain.Tax;
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
 * Tests for tax repository
 */
@SuppressWarnings("nls")
public class TaxRepositoryTest extends UnitilsJUnit4 {

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
     * The method tests if the createTax method works correctly
     */
    @Test
    @DataSet("CleanDB.xml")
    @ExpectedDataSet("tax/TaxRepositoryTest-result.xml")
    public void testCreateTax() {
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        final LocalDate startDate = LocalDate.parse("2002-01-20", formatter);
        final LocalDate endDate = LocalDate.parse("2002-03-20", formatter);
        Assert.assertNotNull("The created tax must not be null.",
                transactionHandler.execute((final Factory factory) -> {
                    final TaxRepository taxRepository = factory.findService(TaxRepository.class);
                    return taxRepository.createTax("tax", startDate, endDate, 1.6, 1.2);
                }));
    }

    /**
     * The method tests if the findUnclosedTaxesWithGivenName method finds only
     * taxes with null end date
     */
    @Test
    @DataSet({ "tax/TaxRepositoryFindTest.xml", "CleanDB.xml" })
    public void testFindTaxWithOneFoundTax() {
        transactionHandler.execute((final Factory factory) -> {
            final TaxRepository taxRepository = factory.findService(TaxRepository.class);
            final List<Tax> taxes = taxRepository.findUnclosedTaxesWithGivenName("tax");
            assertEquals("wrong list size", 1, taxes.size());
            assertNull("the end date must be null", taxes.get(0).getEndDate());
            return taxes;
        });
    }

    /**
     * The method tests if the findUnclosedTaxesWithGivenName method returns
     * empty list when there is no found taxes
     */
    @Test
    @DataSet({ "tax/TaxRepositoryNoFoundTaxesTest.xml", "CleanDB.xml" })
    public void testFindTaxWithNoFoundTaxes() {
        transactionHandler.execute((final Factory factory) -> {
            final TaxRepository taxRepository = factory.findService(TaxRepository.class);
            final List<Tax> taxes = taxRepository.findUnclosedTaxesWithGivenName("tax");
            assertEquals("the list isn't empty", 0, taxes.size());
            return taxes;
        });
    }

    /**
     * The method tests if the getTaxHistory method returns all taxes with this
     * name and only taxes with this name
     *
     */
    @Test
    @DataSet({ "tax/TaxRepositoryFindTest.xml", "CleanDB.xml" })
    public void testGetTaxHistory() {
        transactionHandler.execute((final Factory factory) -> {
            final TaxRepository taxRepository = factory.findService(TaxRepository.class);
            final List<Tax> taxes = taxRepository.getTaxHistory("tax");
            final int listSize = 2;
            assertEquals("wrong list size", taxes.size(), listSize);
            assertPropertyLenientEquals("typeOfTax", Collections.nCopies(listSize, "tax"),
                    taxes);
            return taxes;
        });
    }

    /**
     * Tests if the closeTax method works correctly
     */

    @Test
    @DataSet({ "tax/TaxRepositoryTaxToClose.xml", "CleanDB.xml" })
    @ExpectedDataSet("tax/TaxRepositoryTestClose-result.xml")

    public void testCloseTax() {
        transactionHandler.execute((final Factory factory) -> {
            final TaxRepository taxRepository = factory.findService(TaxRepository.class);
            final LocalDate endDate = LocalDate.of(2017, 01, 20);
            final List<Tax> taxes = taxRepository.findUnclosedTaxesWithGivenName("taxx");
            final boolean isClosed = taxRepository.closeTax(taxes.get(0), endDate);
            Assert.assertTrue("The returned result mustn't be false.", isClosed);
            return new Boolean(isClosed);
        });
    }

    /**
     * Tests if the getAllCurrentTaxes return list with right list size
     */
    @Test
    @DataSet({ "tax/TaxRepositoryCurrentTaxes.xml", "CleanDB.xml" })
    public void testGetCurrentTaxesListSize() {
        transactionHandler.execute((final Factory factory) -> {
            final TaxRepository taxRepository = factory.findService(TaxRepository.class);
            final List<Tax> taxes = taxRepository.getAllCurrentTaxes();
            final int expectedListSize = 2;
            Assert.assertEquals("Wrong list size.", expectedListSize, taxes.size());
            return taxes;
        });
    }

    /**
     * Tests if the getAllCurrentTaxes return taxes with null end date
     */
    @Test
    @DataSet({ "tax/TaxRepositoryCurrentTaxes.xml", "CleanDB.xml" })
    public void testGetCurrentTaxes() {
        transactionHandler.execute((final Factory factory) -> {
            final TaxRepository taxRepository = factory.findService(TaxRepository.class);
            final List<Tax> taxes = taxRepository.getAllCurrentTaxes();
            assertNull("the end date must be null", taxes.get(0).getEndDate());
            return taxes;
        });
    }

}
