package com.dxc.payroll.persistence.domain;

import java.time.LocalDate;

/**
 * Interface for Position Period Entity
 *
 */
public interface PositionPeriod {
    /**
     * getter for UCN
     *
     * @return UCN
     */
    String getUCN();

    /**
     * getter for StartDate
     *
     * @return StartDate
     */
    LocalDate getStartDate();

    /**
     * getter for endDate
     *
     * @return end date
     */
    LocalDate getEndDate();

    /**
     * getter for baseSalary
     *
     * @return base salary
     */
    double getBaseSalary();

    /**
     * getter for jobTitle
     *
     * @return position
     */
    Position getPosition();

    /**
     * getter for Employee
     *
     * @return Employee
     */
    Employee getEmployee();

    /**
     * Set end date of position
     * 
     * @param eDate
     * @return
     */
    void setEndDate(LocalDate eDate);
}
