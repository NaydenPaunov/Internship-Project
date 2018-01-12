package com.dxc.payroll.services.dto;

/**
 * @author milieva5
 *
 */
public class JobDTO {
    /**
     * job title for the job DTO object
     */
    private final String jobTitle;
    /**
     * job degree for the job DTO object
     */
    private final String jobDegree;
    /**
     * minimum salary for the job DTO object
     */
    private final double minSalary;
    /**
     * max salary for the job DTO object
     */
    private final double maxSalary;

    /**
     * Constructor of Job DTO object
     *
     * @param jobTitle
     * @param jobDegree
     * @param minSalary
     * @param maxSalary
     */
    public JobDTO(final String jobTitle, final String jobDegree, final double minSalary,
            final double maxSalary) {
        this.jobTitle = jobTitle;
        this.jobDegree = jobDegree;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    /**
     * @return job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @return job degree
     */
    public String getJobDegree() {
        return jobDegree;
    }

    /**
     * @return min salary
     */
    public double getMinSalary() {
        return minSalary;
    }

    /**
     * @return max salary
     */
    public double getMaxSalary() {
        return maxSalary;
    }
}
