package com.dxc.payroll.persistence.jpa.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityExistsException;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.domain.PositionPeriod;

/**
 * JPA Position Period - Entity.
 *
 * Implements Position Period Interface.
 *
 */
@Entity
@Table(name = "POSITION_PERIOD")
public class JpaPositionPeriod implements PositionPeriod {

    /**
     * Private field - employee of type Employee
     */
    @EmbeddedId
    private JpaPositionPeriodID positionPeriodID;

    /**
     * Private field - endDate of type LocalDate
     */
    @Column(name = "END_DATE")
    private LocalDate endDate;

    /**
     * Private field - baseSalary of type double
     */
    @Column(name = "BASE_SALARY")
    private double baseSalary;

    /**
     * Private field - position of type Position
     */
    @ManyToOne(targetEntity = JpaPosition.class)
    @JoinColumns({ @JoinColumn(name = "JOB_TITLE", referencedColumnName = "JOB_TITLE"),
            @JoinColumn(name = "JOB_DEGREE", referencedColumnName = "JOB_DEGREE"),
            @JoinColumn(name = "JOB_LEVEL", referencedColumnName = "JOB_LEVEL") })
    private Position position;

    /**
     * Private field - employee of type Employee
     */
    @MapsId("ucn")
    @ManyToOne(targetEntity = JpaEmployee.class)
    @JoinColumn(name = "UCN", referencedColumnName = "UCN")
    private Employee employee;

    /**
     * Constructor for JpaPositionPeriod
     */
    protected JpaPositionPeriod() {
        // Needed by JPA
    }

    /**
     * Constructor for JpaPositionPeriod
     *
     * @param startDate
     *            must not be null
     * @param endDate
     * @param baseSalary
     * @param position
     *            must not be null
     * @param employee
     *            must not be null
     */
    public JpaPositionPeriod(final LocalDate startDate, final LocalDate endDate,
            final double baseSalary, final Position position, final Employee employee) {
        assert startDate != null && position != null && employee != null;
        this.positionPeriodID = new JpaPositionPeriodID(employee.getUCN(), startDate);
        this.endDate = endDate;
        this.baseSalary = baseSalary;
        this.position = position;
        this.employee = employee;
    }

    /**
     * Constructor for JpaPositionPeriod
     *
     * @param startDate
     *            must not be null
     * @param baseSalary
     * @param position
     *            must not be null
     * @param employee
     *            must not be null
     */
    public JpaPositionPeriod(final LocalDate startDate, final double baseSalary,
            final Position position, final Employee employee) {
        assert startDate != null && position != null && employee != null;
        this.positionPeriodID = new JpaPositionPeriodID(employee.getUCN(), startDate);
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
    @SuppressWarnings("nls")
    @Override
    public void setEndDate(final LocalDate eDate) {
        assert eDate != null; 
        if (endDate != null) {
            throw new EntityExistsException("End date is already set");
        }
        endDate = eDate;

    }

}
