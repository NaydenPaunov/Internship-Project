package com.dxc.payroll.persistence.jpa.repositories;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

import java.time.LocalDate;
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
import com.dxc.payroll.persistence.domain.Employee;
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
 * Test for methods in EmployeeRepository
 */
@SuppressWarnings("nls")
public class EmployeeRepositoryTest extends UnitilsJUnit4 {

    /**
     * Clean data base file path
     */
    private static final String CLEAN_DB_XML = "CleanDB.xml";

    /**
     * private field - entity manager factory
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
     * private field - transaction handler
     */
    private final TransactionHandler transactionHandler = new TransactionHandlerImpl(
            entityManagerFactory, register);

    /**
     * Test create employee
     *
     */
    @Test
    @DataSet("CleanDB.xml")
    @ExpectedDataSet("employee/EmployeeRepositoryTest-result.xml")
    public void testCreateEmployee() {
        Assert.assertNotNull("An new employee must be created.",
                transactionHandler.execute((final Factory factory) -> {
                    final EmployeeRepository emplRepository = factory
                            .findService(EmployeeRepository.class);
                    return emplRepository.createEmployee("1111221312", "Dragan", "dragan@dxc.com",
                            "qwerty", "CT");
                }));
    }

    /**
     * Test create paycheck
     *
     */
    @Test
    @DataSet({ "CleanDB.xml", "employee/EmployeeRepositoryTest-result.xml" })
    @ExpectedDataSet("paycheck/AddPaycheck-result.xml")
    public void testAddPaycheck() {
        Assert.assertNotNull("An new paycheck must be created.",
                transactionHandler.execute((final Factory factory) -> {
                    final EmployeeRepository emplRepository = factory
                            .findService(EmployeeRepository.class);
                    final Employee employee = emplRepository.findByUCN("1111221312");
                    final LocalDate date = LocalDate.of(2002, 2, 20);
                    return emplRepository.addPaycheck(employee, date, 20, 1000.5, 1100.5, 900.5,
                            800.5);
                }));
    }

    /**
     * Test for findByUCN method in JpaEmployeeRepository when there exists
     * employee with UCN
     */
    @Test
    @DataSet({ "employee/FindByTest-result.xml", CLEAN_DB_XML })
    public void testFindByUCN() {
        Assert.assertNotNull("The searched job must not be null",
                transactionHandler.execute((final Factory factory) -> {
                    final EmployeeRepository employeeRepository = factory
                            .findService(EmployeeRepository.class);
                    final Employee employee = employeeRepository.findByUCN("1111221312");
                    checkEquals(employee, "1111221312", "Dragan", "dragan@dxc.com", "dragan",
                            "CT        ");
                    return employee;
                }));
    }

    /**
     * Test for findByUCN method in JpaEmployeeRepository when there not exists
     * employee with UCN
     */
    @Test
    @DataSet({ "employee/FindByTest-result.xml", CLEAN_DB_XML })
    public void testFindByUCNNoResult() {
        Assert.assertNull("The searched job must be null",
                transactionHandler.execute((final Factory factory) -> {
                    final EmployeeRepository employeeRepository = factory
                            .findService(EmployeeRepository.class);
                    final Employee employee = employeeRepository.findByUCN("0000000000");
                    return employee;
                }));
    }

    /**
     * Test for findByJobTitle method in JpaEmployeeRepository when there exists
     * employees with current employee name
     */
    @Test
    @DataSet({ "employee/FindByNameTest-result.xml", CLEAN_DB_XML })
    public void testFindByName() {
        Assert.assertNotNull("The searched employee must not be null",
                transactionHandler.execute((final Factory factory) -> {
                    final EmployeeRepository employeeRepository = factory
                            .findService(EmployeeRepository.class);
                    final List<Employee> employee = employeeRepository.findByName("Perkan");
                    final int listSize = 2;
                    assertEquals("wrong emplyees size", employee.size(), listSize);
                    assertPropertyLenientEquals("name", Collections.nCopies(listSize, "Perkan"),
                            employee);
                    return employee;
                }));
    }

    /**
     * Test for findByJobTitle method in JpaJobRepository if there does not
     * exists employees with current employee name
     */
    @Test
    @DataSet({ "employee/FindByNameTest-result.xml", CLEAN_DB_XML })
    public void testFindByNameNoResults() {
        transactionHandler.execute((final Factory factory) -> {
            final EmployeeRepository employeeRepository = factory
                    .findService(EmployeeRepository.class);
            final List<Employee> emplyee = employeeRepository.findByName("Sholi");
            assertEquals("wrong emplyees size expected 0", emplyee.size(), 0);
            return emplyee;
        });
    }

    /**
     * Test for findByUCN method in JpaEmployeeRepository when there exists
     * employee with UCN
     */
    @Test
    @DataSet({ "employee/FindByTeamLeadUCN-result.xml", CLEAN_DB_XML })
    public void testFindByTeamLeadUCN() {

        final List<Employee> allEmployeeList = transactionHandler
                .execute((final Factory factory) -> {
                    final EmployeeRepository employeeRepository = factory
                            .findService(EmployeeRepository.class);
                    final List<Employee> employeeList = employeeRepository
                            .findEmployeesByTeamLeadUCN("1111221312");

                    return employeeList;
                });
        final int listSize = 2;
        assertPropertyLenientEquals("teamLeadUCN", Collections.nCopies(listSize, "1111221312"),
                allEmployeeList);
    }

    /**
     * Test for getAllEmployees method in JpaEmployeeRepository when there
     * exists employees in the database
     */
    @Test
    @DataSet({ "employee/GetAllEmployees-result.xml", CLEAN_DB_XML })
    public void testGetAllEmployees() {
        transactionHandler.execute((final Factory factory) -> {
            final EmployeeRepository employeeRepository = factory
                    .findService(EmployeeRepository.class);
            final List<Employee> employeeList = employeeRepository.getAllEmployees();

            assertEquals("Employee names are not matching.", employeeList.get(1).getName(),
                    "Perkan");
            return employeeList;
        });
    }

    /**
     * Test for findByJobTitle method in JpaJobRepository if there does not
     * exists employees with current employee name
     */
    @Test
    @DataSet({ "employee/FindByTeamLeadUCN-result.xml", CLEAN_DB_XML })
    public void testfindByTeamLieadUCNNoResults() {
        transactionHandler.execute((final Factory factory) -> {
            final EmployeeRepository employeeRepository = factory
                    .findService(EmployeeRepository.class);
            final List<Employee> employee = employeeRepository
                    .findEmployeesByTeamLeadUCN("0987654132");
            assertEquals("wrong employees size expected 0", employee.size(), 0);
            return employee;
        });
    }

    // TODO: Change expected and actual.
    private static void checkEquals(final Employee employee, final String ucn, final String name,
            final String username, final String password, final String contractType) {
        assertEquals("wrong employee ucn", employee.getUCN(), ucn);
        assertEquals("wrong employee name", employee.getName(), name);
        assertEquals("wrong employee username", employee.getUserName(), username);
        assertEquals("wrong employee password", employee.getPassword(), password);
        assertEquals("wrong employee contract type", employee.getContractType(), contractType);
    }

}