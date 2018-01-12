package com.dxc.payroll.persistence.repositories;

import java.time.LocalDate;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.domain.PositionPeriod;

/**
 * Interface for Position Period Repository
 *
 */
public interface PositionPeriodRepository {

    /**
     * This method creates new position input
     *
     * @param startDate
     * @param endDate
     * @param baseSalary
     * @param position
     * @param employee
     * @return PositionPeriod object
     */
    PositionPeriod createPositionInput(LocalDate startDate, double baseSalary,
            Position position, Employee employee);
    
    /**
     * Set end date to a positonPeriod
     * @param positionPeriodToClose
     * @param endDate
     * @return boolean
     */
    void setEndDateOfPositionPeriod(PositionPeriod positionPeriodToClose,LocalDate endDate);
}
