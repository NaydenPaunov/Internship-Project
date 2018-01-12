package com.dxc.payroll.persistence.domain;

import java.time.LocalDate;

/**
 * Interface for PositionPeriod primary key.
 *
 */
public interface PositionPeriodID {

    /**
     * getter for the employee UCN
     *
     * @return employee UCN
     */
    String getUCN();

    /**
     * getter for the start date of the period
     *
     * @return start date of the period
     */
    LocalDate getStartDate();

}
