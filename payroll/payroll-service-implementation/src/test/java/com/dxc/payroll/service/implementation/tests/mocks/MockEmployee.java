package com.dxc.payroll.service.implementation.tests.mocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Paycheck;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.domain.PositionPeriod;
import com.dxc.payroll.persistence.domain.Role;

/**
 * Implementation of Employee interface. It is necessary for tests.
 *
 */
public class MockEmployee implements Employee {
    /**
     * private field - UCN of the employee
     */
    private final String ucn;

    /**
     * private field - name of the employee
     */
    private final String name;

    /**
     * private field - userName of the employee
     */
    private final String userName;

    /**
     * private field - password of the employee
     */
    private final String password;

    /**
     * Previous work experience.
     */
    private final int previousWorkExperience;

    /**
     * contract type
     */
    private final String contractType;

    /**
     * Work hours.
     */
    private final int workHours;

    /**
     * Team lead ucn.
     */
    private final String teamLeadUCN;

    /**
     * role of the employee
     */
    private final Role role;

    /**
     * paychecks associated with the employee
     */
    private List<Paycheck> paychecks = new ArrayList<>();

    /**
     * position history for the employee
     */
    private List<PositionPeriod> positionHistory = new ArrayList<>();

    /**
     * Constructor for MockEmployee
     *
     * @param ucn
     *            of type String
     * @param name
     *            of type String
     * @param userName
     *            of type String
     * @param password
     *            of type String
     * @param previousWorkExperience
     *            of type int
     * @param contractType
     *            of type String
     * @param workHours
     *            of type int
     * @param teamLeadUCN
     *            of type String
     * @param role
     *            of type Role
     * @param paychecks
     *            of type list of Paycheks
     * @param positionHistory
     *            of type list of PositionPeriod
     */
    public MockEmployee(final String ucn, final String name, final String userName,
            final String password, final int previousWorkExperience, final String contractType,
            final int workHours, final String teamLeadUCN, final Role role,
            final List<Paycheck> paychecks, final List<PositionPeriod> positionHistory) {
        this.ucn = ucn;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.previousWorkExperience = previousWorkExperience;
        this.contractType = contractType;
        this.workHours = workHours;
        this.teamLeadUCN = teamLeadUCN;
        this.role = role;
        this.paychecks = paychecks;
        this.positionHistory = positionHistory;
    }

    /**
     * Return list of position history.
     */
    @Override
    public List<PositionPeriod> getPositionHistory() {
        return Collections.unmodifiableList(positionHistory);
    }

    /**
     * Get Ucn.
     */
    @Override
    public String getUCN() {
        return ucn;
    }

    /**
     * Get name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get username.
     */
    @Override
    public String getUserName() {
        return userName;
    }

    /**
     * Get password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Get contract type.
     */
    @Override
    public String getContractType() {
        return contractType;
    }

    /**
     * Get paychecks.
     */
    @Override
    public List<Paycheck> getPaychecks() {
        return Collections.unmodifiableList(paychecks);
    }

    /**
     * Get previous work experience
     */
    @Override
    public int getPreviousWorkExperience() {
        return previousWorkExperience;
    }

    /**
     * Get workHours.
     */
    @Override
    public int getWorkHours() {
        return workHours;
    }

    /**
     * Get team lead ucn.
     */
    @Override
    public String getTeamLeadUCN() {
        return teamLeadUCN;
    }

    /**
     * Get role.
     */
    @Override
    public Role getRole() {
        return role;
    }

    /**
     * Get base salary.
     */
    @Override
    public double getBaseSalary() {
        return this.positionHistory.get(0).getBaseSalary();
    }

    /**
     * Get current position.
     */
    @Override
    public Position getCurrentPosition() {
        return positionHistory.get(0).getPosition();
    }

}
