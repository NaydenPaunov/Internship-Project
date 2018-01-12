package com.dxc.payroll.service.implementation.tests.mocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dxc.payroll.persistence.domain.Indexation;
import com.dxc.payroll.persistence.domain.Job;
import com.dxc.payroll.persistence.domain.JobID;
import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.domain.PositionPeriod;

/**
 * Implementation of Position interface. It is necessary for tests.
 *
 */
public class MockPosition implements Position {

    /**
     * Private field positionPk of type PositionID.
     *
     */
    private final MockPositionID positionPK;

    /**
     * Private field job of type MockJob.
     *
     */
    private final MockJob job;

    /**
     * Private field - baseSalary of type double.
     */
    private final double baseSalary;

    /**
     * Private field - List of indexations.
     *
     */
    private final List<Indexation> indexations = new ArrayList<>();

    /**
     * Private field - List of position periods.
     */
    private final List<PositionPeriod> positionPeriod = new ArrayList<>();

    /**
     * Constructor for the MockPosition class.
     *
     * @param job
     *            job must not be null
     * @param jobLevel
     *            job level must not be null
     * @param baseSalary
     *            baseSalary must not be null
     *
     */
    public MockPosition(final Job job, final int jobLevel, final double baseSalary) {
        this.positionPK = new MockPositionID(new MockJobId(job.getJobTitle(), job.getJobDegree()),
                jobLevel);
        this.job = (MockJob) job;
        this.baseSalary = baseSalary;
    }

    /**
     * @see com.dxc.payroll.persistence.domain.Position#getJob()
     *
     */
    @Override
    public Job getJob() {
        return job;
    }

    /**
     * Gets the job level.
     *
     * @return job level of type int
     *
     */
    @Override
    public int getJobLevel() {
        return positionPK.getJobLevel();
    }

    /**
     * Gets the base salary.
     *
     * @return Base Salary of type double
     */
    @Override
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * Gets the position history.
     *
     * @return The List of position periods.
     */
    public List<PositionPeriod> getPositionPeriod() {
        return Collections.unmodifiableList(positionPeriod);
    }

    /**
     * Gets the indexations.
     *
     * @return The list of indexations.
     */
    public List<Indexation> getIndexations() {
        return Collections.unmodifiableList(indexations);
    }

}

/**
 * This class represents the primary key for the Position class
 */
class MockPositionID {

    /**
     * Private field - jobId of type JobID.
     */
    private final JobID jobId;

    /**
     * Private field - Job Level of the position.
     */
    private final int jobLevel;

    /**
     * Constructor for the PositionID class.
     *
     * @param jobId
     *            must not be null.
     * @param jobLevel
     *            Job Level of the position, must not be null.
     */
    public MockPositionID(final JobID jobId, final int jobLevel) {
        this.jobId = jobId;
        this.jobLevel = jobLevel;
    }

    /**
     * Gets the jobId.
     */
    public JobID getJob() {
        return jobId;
    }

    /**
     * Gets the Job Level.
     */
    public int getJobLevel() {
        return jobLevel;
    }
}
