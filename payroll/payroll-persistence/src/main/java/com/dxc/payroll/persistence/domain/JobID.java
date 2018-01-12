package com.dxc.payroll.persistence.domain;

/**
 * Interface for the ID of the Job class. It provides information about the
 * primary key of the Job Entity - the job title and the job degree.
 */
public interface JobID {
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

}
