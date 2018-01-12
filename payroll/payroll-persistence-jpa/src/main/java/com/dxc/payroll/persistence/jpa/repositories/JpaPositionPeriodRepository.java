package com.dxc.payroll.persistence.jpa.repositories;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import com.dxc.payroll.persistence.domain.Employee;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.domain.PositionPeriod;
import com.dxc.payroll.persistence.jpa.domain.JpaPositionPeriod;
import com.dxc.payroll.persistence.repositories.PositionPeriodRepository;

/**
 * JPA implementation for Position Period Repository
 *
 */
public final class JpaPositionPeriodRepository implements PositionPeriodRepository {

    /**
     * Private field - entityManager of type EntityManager
     */
    private final EntityManager entityManager;

    /**
     * Constructor for JpaPositionPeriodRepository
     *
     * @param entityManager
     *            must not be null
     */
    public JpaPositionPeriodRepository(final EntityManager entityManager) {
        assert entityManager != null;
        this.entityManager = entityManager;
    }

    /**
     * Set end date to a positonPeriod
     *
     * @param positionPeriodToClose
     * @param endDate
     * @return boolean
     */
    @Override
    public void setEndDateOfPositionPeriod(final PositionPeriod positionPeriodToClose,
            final LocalDate endDate) {
        positionPeriodToClose.setEndDate(endDate);
    }

    /**
     * This method creates new position input
     *
     * @param employee
     *            must not be null
     * @param startDate
     *            must not be null
     * @param endDate
     * @param baseSalary
     * @param jobTitle
     *            must not be null
     * @param jobLevel
     * @return PositionPeriod object
     */
    @Override
    public PositionPeriod createPositionInput(final LocalDate startDate, final double baseSalary,
            final Position position, final Employee employee) {
        final PositionPeriod positionPeriod = new JpaPositionPeriod(startDate, baseSalary, position,
                employee);
        entityManager.persist(positionPeriod);
        return positionPeriod;
    }
}
