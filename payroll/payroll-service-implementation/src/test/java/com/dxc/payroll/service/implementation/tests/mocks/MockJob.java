package com.dxc.payroll.service.implementation.tests.mocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dxc.payroll.persistence.domain.Job;
import com.dxc.payroll.persistence.domain.JobID;
import com.dxc.payroll.persistence.domain.Position;

/**
 * Implementation of Job interface. It is necessary for tests.
 *
 */
public class MockJob implements Job {

    /**
     * Private field - jobId of type JobID - Embedded class for the Job PK.
     */
    private final JobID jobId;

    /**
     * Private field - precedenceNumber of type int.
     */
    private final int precedenceNumber;

    /**
     * Private field - minSalary of type double. column in the database
     */
    private final double minSalary;

    /**
     * Private field - maxSalary of type double. column in the database
     */
    private final double maxSalary;

    /**
     * List of positions for the given job.
     */
    private final List<Position> positions = new ArrayList<>();

    /**
     * Constructor for the MockJob class.
     *
     * @param jobTitle
     *            of type String, must not be null.
     * @param jobDegree
     *            of type String, must not be null.
     * @param precedenceNumber
     *            of type int.
     * @param minSalary
     *            of type double.
     * @param maxSalary
     *            of type double.
     */
    public MockJob(final String jobTitle, final String jobDegree, final int precedenceNumber,
            final double minSalary, final double maxSalary) {
        this.jobId = new MockJobId(jobTitle, jobDegree);
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

/**
 * This class represents the primary key for the Job class
 */
class MockJobId implements JobID {

    /**
     * private field - Title of the job.
     */
    private final String jobTitle;

    /**
     * private field - Degree of the job.
     */
    private final String jobDegree;

    /**
     *
     * Constructor for MockJobId.
     *
     * @param jobTitle
     *            of type String, must not be null.
     * @param jobDegree
     *            of type String, must not be null.
     */
    public MockJobId(final String jobTitle, final String jobDegree) {
        this.jobTitle = jobTitle;
        this.jobDegree = jobDegree;
    }

    /**
     * @see com.dxc.payroll.persistence.domain.JobID#getJobTitle()
     */
    @Override
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @see com.dxc.payroll.persistence.domain.JobID#getJobDegree()
     */
    @Override
    public String getJobDegree() {
        return jobDegree;
    }
}
