package com.dxc.payroll.services.dto;

import java.util.List;

/**
 *
 */
public class JobWithSalaryDTO {
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
     * list of levelAndSalaryDTOs
     */
    private final List<LevelAndSalaryDTO> levelAndSalaryDTOs;

    /**
     * Constructor for JobForProffesionDTO
     *
     * @param jobDegree
     * @param minSalary
     * @param maxSalary
     * @param levelAndSalaryDTOs
     */
    public JobWithSalaryDTO(String jobDegree, double minSalary, double maxSalary,
            List<LevelAndSalaryDTO> levelAndSalaryDTOs) {
        super();
        this.jobDegree = jobDegree;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.levelAndSalaryDTOs = levelAndSalaryDTOs;
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

    /**
     * @return list of levelAndSalaryDTOs
     */
    public List<LevelAndSalaryDTO> getLevelAndSalaryDTOs() {
        return levelAndSalaryDTOs;
    }

}
