package com.dxc.payroll.persistence.jpa.repositories;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Paycheck;
import com.dxc.payroll.persistence.jpa.domain.JpaEmployee;
import com.dxc.payroll.persistence.jpa.domain.JpaPaycheck;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;

/**
 * @author sshishkov
 *
 */
public final class JpaEmployeeRepository implements EmployeeRepository {

    @SuppressWarnings("nls")
    private static final String QRY_FIND_EMPLOYEE_BY_NAME = "qryFindEmployeeByName";
    @SuppressWarnings("nls")
    private static final String EMPLOYEE_NAME = "name";
    @SuppressWarnings("nls")

    /**
     * Query that find employees.
     */
    private static final String QRY_FIND_EMPLOYEES_BY_TEAM_LEAD_UCN = "qryFindEmployeesByTeamLeadUCN";

    /**
     * Query to list all employees.
     */
    @SuppressWarnings("nls")
    private static final String QRY_GET_ALL_EMPLOYEES = "qryGetAllEmployees";

    /**
     * Team lead ucn.
     */
    @SuppressWarnings("nls")
    private static final String TEAM_LEAD_UCN = "teamLeadUCN";

    private final EntityManager entityManager;

    /**
     * @param entityManager
     */
    public JpaEmployeeRepository(final EntityManager entityManager) {
        assert entityManager != null;
        this.entityManager = entityManager;
    }

    @Override
    public Employee createEmployee(final String UCN, final String name, final String userName,
            final String password, final String contractType) {
        final Employee employee = new JpaEmployee(UCN, name, userName, password, contractType);
        entityManager.persist(employee);
        return employee;
    }

    @Override
    public Employee findByUCN(final String UCN) {
        return entityManager.find(JpaEmployee.class, UCN);
    }

    @Override
    public List<Employee> findByName(final String name) {
        return entityManager.createNamedQuery(QRY_FIND_EMPLOYEE_BY_NAME, Employee.class)
                .setParameter(EMPLOYEE_NAME, name).getResultList();
    }

    /**
     * Creates new JpaPaycheck and persists it in the database
     */
    @Override
    public Paycheck addPaycheck(final Employee employee, final LocalDate date,
            final int hoursWorked, final double baseSalary, final double grossSalary,
            final double taxRate, final double netSalary) {
        final Paycheck pay = new JpaPaycheck(employee, date, hoursWorked, baseSalary, grossSalary,
                taxRate, netSalary);
        entityManager.persist(pay);
        return pay;
    }

    /**
     * The method find employees by team lead ucn.
     */
    @Override
    public List<Employee> findEmployeesByTeamLeadUCN(final String teamLeadUCN) {
        return entityManager.createNamedQuery(QRY_FIND_EMPLOYEES_BY_TEAM_LEAD_UCN, Employee.class)
                .setParameter(TEAM_LEAD_UCN, teamLeadUCN).getResultList();
    }

    /**
     * @see com.dxc.payroll.persistence.repositories.EmployeeRepository#getAllEmployees()
     */
    @Override
    public List<Employee> getAllEmployees() {
        return entityManager.createNamedQuery(QRY_GET_ALL_EMPLOYEES, Employee.class)
                .getResultList();
    }

}
