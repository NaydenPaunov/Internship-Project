package com.dxc.payroll.persistence.jpa.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dxc.payroll.persistence.domain.Job;
import com.dxc.payroll.persistence.domain.JobID;
import com.dxc.payroll.persistence.domain.Position;

/**
 * Job Entity.
 */
@Entity
@Table(name = "JOB")
/**
 *
 * Named Queries for the JOB table.
 *
 */
@NamedQueries({
        @NamedQuery(name = "qryFindJobsByJobTitle",
                query = "SELECT j from JpaJob j WHERE j.jobId.jobTitle = :jobTitle"),
        @NamedQuery(name = "qryListJobTitles",
                query = "SELECT DISTINCT j.jobId.jobTitle FROM JpaJob j "
                        + "ORDER BY j.jobId.jobTitle"),
        @NamedQuery(name = "qryGetJobDegrees",
                query = "SELECT j.jobId.jobDegree FROM JpaJob j WHERE j.jobId.jobTitle = :jobTitle "
                        + "ORDER BY j.precedenceNumber"),
        @NamedQuery(name = "qryFindAllJobs", query = "SELECT j FROM JpaJob j") })

public class JpaJob implements Job {

    /**
     * Private field - jobId of type JpaJobID - Embedded class for the Job PK.
     * Primary key of JOB (JOB_TITLE, JOB_DEGREE)
     */
    @EmbeddedId
    private JpaJobID jobId;

    /**
     * Private field - precedenceNumber of type int, corresponding to the
     * PRECEDENSE_NUMBER column in the database
     */
    @Column(name = "PRECEDENSE_NUMBER")
    private int precedenceNumber;

    /**
     * Private field - minSalary of type double, corresponding to the MIN_SALARY
     * column in the database
     */
    @Column(name = "MIN_SALARY")
    private double minSalary;

    /**
     * Private field - maxSalary of type double, corresponding to the MAX_SALARY
     * column in the database
     */
    @Column(name = "MAX_SALARY")
    private double maxSalary;

    /**
     * List of positions for the given job.
     */
    @OneToMany(mappedBy = "job", targetEntity = JpaPosition.class)
    private List<Position> positions = new ArrayList<>();

    /**
     * Default constructor.
     *
     * Needed by JPA
     */
    protected JpaJob() {

    }

    /**
     * Constructor for the JpaJob class. It accepts jobTitle, jobDegree,
     * precedenceNumber, minSalary and maxSalary as parameters.
     *
     * @param jobTitle
     * @param jobDegree
     * @param precedenceNumber
     * @param minSalary
     * @param maxSalary
     */
    public JpaJob(final String jobTitle, final String jobDegree, final int precedenceNumber,
            final double minSalary, final double maxSalary) {
        this.jobId = new JpaJobID(jobTitle, jobDegree);
        this.precedenceNumber = precedenceNumber;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    /**
     * @see com.dxc.payroll.persistence.domain.Job#getJobTitle()
     */
    @Override
    public String getJobTitle() {
        return jobId.getJobTitle();
    }

    /**
     * @see com.dxc.payroll.persistence.domain.Job#getJobDegree()
     */
    @Override
    public String getJobDegree() {
        return jobId.getJobDegree();
    }

    /**
     * @see com.dxc.payroll.persistence.domain.Job#getJobDegree()
     */
    @Override
    public int getPrecedenceNumber() {
        return precedenceNumber;
    }

    /**
     * @see com.dxc.payroll.persistence.domain.Job#getMinSalary()
     */
    @Override
    public double getMinSalary() {
        return minSalary;
    }

    /**
     * @see com.dxc.payroll.persistence.domain.Job#getMaxSalary()
     */
    @Override
    public double getMaxSalary() {
        return maxSalary;
    }

    /**
     * @see com.dxc.payroll.persistence.domain.Job#getJobID()
     */
    @Override
    public JobID getJobID() {
        return jobId;
    }

    /**
     * @see com.dxc.payroll.persistence.domain.Job#getPositions()
     */
    @Override
    public List<Position> getPositions() {
        return Collections.unmodifiableList(positions);
    }

}
