package com.dxc.payroll.persistence.jpa.domain;

import static java.util.Objects.hash;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Composite primary key for Indexation entity.
 *
 */
@Embeddable
public class IndexationID implements Serializable {

    /**
     * Generated serial version ID for IndexationID.
     */
    private static final long serialVersionUID = 5934559293996606432L;

    /**
     * private field - Date of the indexation.
     */
    @Column(name = "DATE_OF_INDEXATION")
    private LocalDate dateOfIndexation;

    /**
     * private field - positionId of type PositionID. Primary key of POSITION
     * (JOB_TITLE, JOB_DEGREE, JOB_LEVEL)
     */
    private PositionID positionId;

    /**
     * Used by JPA.
     */
    public IndexationID() {
        // Required by JPA.
    }

    /**
     * Constructor for the IndexationID class.
     *
     * @param positionId
     *            Primary key of the position, must not be null.
     *
     * @param dateOfIndexation
     *            Date of the indexation, must not be null.
     */
    public IndexationID(final PositionID positionId, final LocalDate dateOfIndexation) {
        this.positionId = positionId;
        this.dateOfIndexation = dateOfIndexation;
    }

    /**
     * Getter for the primary key of the position.
     *
     * @return Primary key of the position.
     */
    public PositionID getPositionID() {
        return positionId;
    }

    /**
     * Getter for the date of indexation.
     *
     * @return date of indexation
     */
    public LocalDate getDateOfIndexation() {
        return dateOfIndexation;
    }

    /**
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof IndexationID)) {
            return false;
        }
        final IndexationID indexationID = (IndexationID) other;
        return indexationID.dateOfIndexation.equals(this.dateOfIndexation)
                && indexationID.positionId.equals(this.positionId);
    }

    /**
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return hash(positionId, dateOfIndexation);
    }

}
