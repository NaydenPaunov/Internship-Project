package com.dxc.payroll.persistence.domain;

import java.util.List;

/**
 * Interface for the Job Entity. This class provides information about the jobs
 * in the system. The user can access the job title, job degree, precedence
 * number, minimum and maximum salary and jobId for the given job. It also
 * provides a list containing all current positions for the given job.
 */
public interface Job {
    /**
     * Gets the job title for the given job.
     *
     * @return job title
     */
    String getJobTitle();

    /**
     * Gets the job degree for the given job.
     *
     * @return job degree
     */
    String getJobDegree();

    /**
     * Gets the precedence number for the given job.
     *
     * @return precedence number
     */
    int getPrecedenceNumber();

    /**
     * Gets the minimum salary for the given job.
     *
     * @return minimum salary
     */
    double getMinSalary();

    /**
     * Gets the maximum salary for the given job.
     *
     * @return maximum salary
     */
    double getMaxSalary();

    /**
     * Gets the jobID for the given job.
     *
     * @return JobID
     * @see JobID
     */
    JobID getJobID();

    /**
     * Gets the list of all positions for the given job.
     *
     * @return list of positions
     */
    List<Position> getPositions();

}
