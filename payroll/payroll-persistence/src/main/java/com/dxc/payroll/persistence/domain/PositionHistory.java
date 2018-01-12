package com.dxc.payroll.persistence.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Interface for Position History Entity
 *
 * @author dtanev
 */
public interface PositionHistory {

    /**
     * getter for UCN
     */
    String getUCN();

    /**
     * getter for startDate
     */
    Date getStartDate();

    /**
     * getter for endDate
     */
    Date getEndDate();

    /**
     * getter for baseSalary
     */
    BigDecimal getBaseSalary();

    /**
     * getter for jobTitle
     */
    String getJobTitle();

    /**
     * getter for jobLevel
     */
    Integer getJobLevel();

}
