package com.dxc.payroll.persistence.domain;

/**
 * The Interface Position.
 */
public interface Position {

    /**
     * Get job level.
     *
     * @return Job Level
     */
    int getJobLevel();

    /**
     * Get Base Salary.
     *
     * @return Base Salary
     */
    double getBaseSalary();

    /**
     * Gets the job.
     *
     * @return Job
     */
    Job getJob();

}
