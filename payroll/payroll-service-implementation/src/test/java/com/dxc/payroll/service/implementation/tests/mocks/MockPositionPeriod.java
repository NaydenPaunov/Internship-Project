package com.dxc.payroll.service.implementation.tests.mocks;

import java.time.LocalDate;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.domain.PositionPeriod;
import com.dxc.payroll.persistence.domain.PositionPeriodID;

/**
 * Implementation of PositionPeriod interface. It is necessary for tests.
 *
 */
public class MockPositionPeriod implements PositionPeriod {

    /**
     * Private field - employee of type Employee
     */
    private final MockPositionPeriodID positionPeriodID;

    /**
     * Private field - endDate of type LocalDate
     */
    private LocalDate endDate;

    /**
     * Private field - baseSalary of type double
     */
    private final double baseSalary;

    /**
     * Private field - position of type Position
     */
    private final Position position;

    /**
     * Private field - employee of type Employee
     */
    private final Employee employee;

    /**
     * Constructor for MockPositionPeriod
     *
     * @param startDate of type LocalDate
     * @param endDate of type LocalDate
     * @param baseSalary of type LocalDate
     * @param position of type Position
     * @param employee of type Employee
     */
    public MockPositionPeriod(final LocalDate startDate, final LocalDate endDate,
            final double baseSalary, final Position position, final Employee employee) {
        this.positionPeriodID = new MockPositionPeriodID(employee.getUCN(), startDate);
        this.endDate = endDate;
        this.baseSalary = baseSalary;
        this.position = position;
        this.employee = employee;
    }

    /**
     * getter for UCN
     *
     */
    @Override
    public String getUCN() {
        return positionPeriodID.getUCN();
    }

    /**
     * getter for startDate
     *
     */
    @Override
    public LocalDate getStartDate() {
        return positionPeriodID.getStartDate();
    }

    /**
     * getter for endDate
     *
     */
    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * getter for baseSalary
     *
     */
    @Override
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * getter for Position
     *
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * getter for Employee
     *
     */
    @Override
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set end date of position
     */
    @Override
    public void setEndDate(final LocalDate eDate) {
        assert eDate != null;
        if (endDate != null) {
            // throw new EntityExistsException("End date is already set");
        }
        endDate = eDate;

    }

}

class MockPositionPeriodID implements PositionPeriodID {
    /**
     * Private field - employee of type String.
     */
    private final String ucn;

    /**
     * Private field - Start date of the period.
     */
    private final LocalDate startDate;

    /**
     * Constructor for JpaPositionPeriodID class
     *
     * @param ucn
     *            of type String.
     * @param startDate
     *            of type LocalDate.
     */
    public MockPositionPeriodID(final String ucn, final LocalDate startDate) {
        this.ucn = ucn;
        this.startDate = startDate;
    }

    /**
     * getter for the employee UCN.
     */
    @Override
    public String getUCN() {
        return ucn;
    }

    /**
     * getter for the Start date of the period.
     */
    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

}
