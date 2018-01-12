package com.dxc.payroll.persistence.jpa.domain;

import static java.util.Objects.hash;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Embedded class for composite id key in Position entity.
 */
@Embeddable
public class PositionID implements Serializable {

    /**
     * Generated serial version ID for PositionID.
     */
    private static final long serialVersionUID = -2762228584743425244L;

    /**
     * Private field - jobId of type JpaJobID. PK of JOB (JOB_TITLE, JOB_DEGREE)
     */
    private JpaJobID jobId;

    /**
     * Private field - Job Level of the position.
     */
    @Column(name = "JOB_LEVEL")
    private int jobLevel;

    /**
     * Used by JPA.
     */
    public PositionID() {
        // Required by JPA.
    }

    /**
     * Constructor for the PositionID class.
     *
     * @param jobId
     *            must not be null.
     * @param jobLevel
     *            Job Level of the position, must not be null.
     */
    public PositionID(final JpaJobID jobId, final int jobLevel) {
        assert jobId != null;
        this.jobId = jobId;
        this.jobLevel = jobLevel;
    }

    /**
     * Gets the jobId.
     *
     * @return jobId
     */
    public JpaJobID getJob() {
        return jobId;
    }

    /**
     * Gets the Job Level.
     *
     * @return jobLevel
     */
    public int getJobLevel() {
        return jobLevel;
    }

    /**
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof PositionID)) {
            return false;
        }
        final PositionID positionKey = (PositionID) obj;
        return positionKey.jobLevel == this.jobLevel && positionKey.jobId.equals(this.jobId);
    }

    /**
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return hash(jobId, Integer.valueOf(jobLevel));
    }

}
