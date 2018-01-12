package com.dxc.payroll.persistence.domain;

import java.time.LocalDate;

/**
 * @author vpazderska
 *
 */
public interface Indexation {

    /**
     * @return percentage
     */
    double getPercentage();

    /**
     * @return dateOfIndexation
     */
    LocalDate getDateOfIndexation();

    /**
     * @return position
     */
    Position getPosition();

}
