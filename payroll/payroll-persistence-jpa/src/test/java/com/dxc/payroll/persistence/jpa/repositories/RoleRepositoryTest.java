package com.dxc.payroll.persistence.jpa.repositories;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

import java.util.Arrays;
import java.util.List;

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
import com.dxc.payroll.persistence.domain.Role;
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
 * Tests for all methods in RoleRepository
 */
public class RoleRepositoryTest extends UnitilsJUnit4 {
    /**
     * Clean data base file path
     */
    @SuppressWarnings("nls")
    private static final String CLEAN_DB_XML = "CleanDB.xml";
    /**
     * private field - entity manager factory
     */
    @SuppressWarnings("nls")
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
            entityManagerFactory,register);

    /**
     * Test for create role
     *
     */
    @SuppressWarnings("nls")
    @Test
    @DataSet(CLEAN_DB_XML)
    @ExpectedDataSet("rights/CreateRoleTest.xml")
    public void testCreateRole() {
        Assert.assertNotNull("The created Role must not be null.",
                transactionHandler.execute((final Factory factory) -> {
                    final RoleRepository roleRepository = factory.findService(RoleRepository.class);
                    return roleRepository.createRole("Manager", "Description");
                }));
    }

    /**
     * Test for findByName method in JpaRoleRepository when there exists role
     * with current role name
     */
    @SuppressWarnings("nls")
    @Test
    @DataSet({ "rights/FindRoleByNameTest.xml", CLEAN_DB_XML })
    public void testFindRoleByName() {
        Assert.assertNotNull("The searched role must not be null",
                transactionHandler.execute((final Factory factory) -> {
                    final RoleRepository roleRepository = factory.findService(RoleRepository.class);
                    final Role role = roleRepository.findRoleByName("Manager");
                    checkEquals(role, "Manager", "Description");
                    return role;
                }));
    }

    /**
     * Test for findByName method in JpaRoleRepository when there does not
     * exists role with current role name
     */
    @SuppressWarnings("nls")
    @Test
    @DataSet({ "rights/FindRoleByNameTest.xml", CLEAN_DB_XML })
    public void testFindRoleByNameNoResult() {
        Assert.assertNull("The searched role must  be null",
                transactionHandler.execute((final Factory factory) -> {
                    final RoleRepository roleRepository = factory.findService(RoleRepository.class);
                    final Role role = roleRepository.findRoleByName("Managerr");
                    return role;
                }));
    }

    /**
     * Test for getAllRoles method in JpaJobRepository when there exists roles
     */
    @SuppressWarnings("nls")
    @Test
    @DataSet({ "rights/GetAllRolesTest.xml", CLEAN_DB_XML })
    public void testGetAllRoles() {
        Assert.assertNotNull("The searched job must not be null",
                transactionHandler.execute((final Factory factory) -> {
                    final RoleRepository roleRepository = factory.findService(RoleRepository.class);
                    final List<Role> roles = roleRepository.getAllRoles();
                    final int listSize = 3;
                    assertEquals("wrong roles number", listSize, roles.size());
                    assertPropertyLenientEquals("roleName",
                            Arrays.asList("Manager",
                                    "ACOUNTANT",
                                    "EMPLOYEE"),
                            roles);
                    return roles;
                }));
    }

    /**
     * Test for getAllRoles method in JpaJobRepository when there does not
     * exists roles
     */
    @SuppressWarnings("nls")
    @Test
    @DataSet({ "rights/GetAllRolesNoResultTest.xml", CLEAN_DB_XML })
    public void testGetAllRolesNoResult() {
        final List<Role> allRoles = transactionHandler
                .execute((final Factory factory) -> {
                    final RoleRepository roleRepository = factory.findService(RoleRepository.class);
                    final List<Role> roles = roleRepository.getAllRoles();
                    return roles;
                });
        assertEquals("wrong roles size expected 0", 0, allRoles.size());
    }

    @SuppressWarnings("nls")
    private static void checkEquals(final Role role, final String roleType,
            final String roleDescription) {
        assertEquals("wrong role name", roleType, role.getRoleName());
        assertEquals("wrong role Description ", roleDescription, role.getDescription());
    }

}
