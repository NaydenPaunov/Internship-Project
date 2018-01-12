package com.dxc.payroll.persistence.jpa.domain;

import static java.util.Objects.hash;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.dxc.payroll.persistence.domain.PositionPeriodID;

/**
 * Composite primary key for the PositionPeriod entity.
 *
 */
@Embeddable
public class JpaPositionPeriodID implements Serializable, PositionPeriodID {

    /**
     * Generated serial version ID for JpaPositionPeriodID.
     */
    private static final long serialVersionUID = -5728022391977789097L;

    /**
     * Private field - employee of type String. Needs to store employee UCN. It
     * must be the same name as id employee from JpaPositionPeriod class.
     */
    @Column(name = "UCN")
    private String ucn;

    /**
     * Private field - Start date of the period.
     */
    @Column(name = "START_DATE")
    private LocalDate startDate;

    /**
     * Used by JPA.
     */
    public JpaPositionPeriodID() {
        // Required by JPA.
    }

    /**
     * Constructor for JpaPositionPeriodID class
     *
     * @param ucn
     *            ucn of the employee, must not be null.
     * @param startDate
     *            The start date of the period, must not be null.
     */
    public JpaPositionPeriodID(final String ucn, final LocalDate startDate) {
        assert ucn != null && startDate != null;
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

    /**
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof JpaPositionPeriodID)) {
            return false;
        }
        final JpaPositionPeriodID positionPeriodID = (JpaPositionPeriodID) other;
        return positionPeriodID.ucn.equals(ucn) && positionPeriodID.startDate.equals(startDate);
    }

    /**
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return hash(ucn, startDate);
    }

}
