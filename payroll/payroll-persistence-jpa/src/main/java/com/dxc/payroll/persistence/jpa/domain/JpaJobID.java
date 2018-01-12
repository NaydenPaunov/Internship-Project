package com.dxc.payroll.persistence.jpa.domain;

import static java.util.Objects.hash;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.dxc.payroll.persistence.domain.JobID;
import com.dxc.payroll.persistence.jpa.constants.PayrollPersistenceConstants;
import com.dxc.payroll.persistence.jpa.utils.Utils;

/**
 * Composite primary key for the Job entity.
 *
 */
@Embeddable
public class JpaJobID implements Serializable, JobID {

    /**
     * Generated serial version ID for JpaJobID.
     */
    private static final long serialVersionUID = 6069485206483714340L;

    /**
     * private field - Title of the job.
     */
    @Column(name = "JOB_TITLE")
    private String jobTitle;

    /**
     * private field - Degree of the job
     */
    @Column(name = "JOB_DEGREE")
    private String jobDegree;

    /**
     * Default constructor for JpaJobID
     *
     * Used by JPA.
     */
    public JpaJobID() {
        // Required by JPA.
    }

    /**
     *
     * Constructor for JpaJobID. It accepts two parameters - jobTitle and
     * JobDegree- the primary key of JOB
     *
     * @param jobTitle
     *            Title of the job, must not be null.
     * @param jobDegree
     *            Degree of the job, must not be null.
     */
    public JpaJobID(final String jobTitle, final String jobDegree) {
        this.jobTitle = Utils.padRight(jobTitle, PayrollPersistenceConstants.JOB_TITLE_LENGTH);
        this.jobDegree = Utils.padRight(jobDegree, PayrollPersistenceConstants.JOB_DEGREE_LENGTH);
    }

    /**
     *
     * @see com.dxc.payroll.persistence.domain.JobID#getJobTitle()
     */
    @Override
    public String getJobTitle() {
        return jobTitle.trim();
    }

    /**
     *
     * @see com.dxc.payroll.persistence.domain.JobID#getJobDegree()
     */
    @Override
    public String getJobDegree() {
        return jobDegree.trim();
    }

    /**
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof JpaJobID)) {
            return false;
        }
        final JpaJobID jobID = (JpaJobID) other;
        return jobID.jobTitle.equals(jobTitle) && jobID.jobDegree.equals(jobDegree);
    }

    /**
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return hash(jobTitle, jobDegree);
    }

}
