package com.dxc.payroll.persistence.jpa.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Paycheck;

/**
 * JPA Implementation of the Paycheck entity.
 *
 */
@Entity
@Table(name = "PAYCHECK")
@IdClass(PaycheckID.class)
public class JpaPaycheck implements Paycheck {

    /**
     * Private field - employee of type double, corresponding to the UCN column
     * in the database
     */
    @Id
    @ManyToOne(targetEntity = JpaEmployee.class)
    @JoinColumn(name = "UCN")
    private Employee employee;

    /**
     * Private field - date of type double, corresponding to the
     * DATE_OF_PAYCHECK column in the database
     */
    @Id
    @Column(name = "DATE_OF_PAYCHECK")
    private LocalDate date;
    /**
     * Private field - hoursWorked of type double, corresponding to the
     * HOURS_WORKED column in the database
     */
    @Column(name = "HOURS_WORKED")
    private int hoursWorked;

    /**
     * * Private field - baseSalary of type double, corresponding to the
     * BASE_SALARY column in the database
     */
    @Column(name = "BASE_SALARY")
    private double baseSalary;

    /**
     * Private field - grossSalary of type double, corresponding to the
     * GROSS_SALARY column in the database
     */
    @Column(name = "GROSS_SALARY")
    private double grossSalary;

    /**
     * Private field - taxRate of type double, corresponding to the TAX_RATE
     * column in the database
     */
    @Column(name = "TAX_RATE")
    private double taxRate;

    /**
     * Private field - netSalary of type double, corresponding to the NET_SALARY
     * column in the database
     */
    @Column(name = "NET_SALARY")
    private double netSalary;

    /**
     * Needed by JPA
     */
    protected JpaPaycheck() {
    }

    /**
     * Constructor for JpaPaycheck
     * 
     * @param employee
     * @param date
     * @param hoursWorked
     * @param baseSalary
     * @param grossSalary
     * @param taxRate
     * @param netSalary
     */
    public JpaPaycheck(final Employee employee, final LocalDate date, final int hoursWorked,
            final double baseSalary, final double grossSalary, final double taxRate,
            final double netSalary) {
        assert employee != null && date != null && hoursWorked >= 0 && baseSalary > 0;

        this.employee = employee;
        this.date = date;
        this.hoursWorked = hoursWorked;
        this.baseSalary = baseSalary;
        this.grossSalary = grossSalary;
        this.taxRate = taxRate;
        this.netSalary = netSalary;
    }

    /**
     * get Employee
     */
    @Override
    public Employee getEmployee() {
        return employee;
    }

    /**
     * get Date
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * get hoursWorked
     */
    @Override
    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * get baseSalary
     */
    @Override
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * get gross salary
     */
    @Override
    public double getGrossSalary() {
        return grossSalary;
    }

    /**
     * get tax rate
     */
    @Override
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * get net salary
     */
    @Override
    public double getNetSalary() {
        return netSalary;
    }
}
