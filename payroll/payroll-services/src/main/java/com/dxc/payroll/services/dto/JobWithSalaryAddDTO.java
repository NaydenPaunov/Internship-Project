package com.dxc.payroll.services.dto;

import java.util.Collections;
import java.util.List;

/**
 * Job with salary DTO necessary for add new position
 */
public class JobWithSalaryAddDTO {
    /**
     * job degree of type String
     */
    private final String jobDegree;
    /**
     * minimum salary of type double
     */
    private final double minSalary;
    /**
     * max salary of type double
     */
    private final double maxSalary;

    /**
     * list of levelAndSalaryDTOs
     */
    private final List<LevelAndSalaryDTO> levelAndSalaryDTOs;

    /**
     * Private field of type int
     */
    private final int jobLevelToBeAdded;

    /**
     * Private field of type double
     */
    private final double minSalaryToBeChecked;

    /**
     * Constructor for JobForProffesionDTO
     *
     * @param jobDegree,
     *            must not be null ot empty
     * @param minSalary
     * @param maxSalary
     * @param levelAndSalaryDTOs
     */
    public JobWithSalaryAddDTO(final String jobDegree, final double minSalary,
            final double maxSalary, final List<LevelAndSalaryDTO> levelAndSalaryDTOs,
            final int jobLevelToBeAdded, final double minSalaryToBeChecked) {
        this.jobDegree = jobDegree;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.levelAndSalaryDTOs = Collections.unmodifiableList(levelAndSalaryDTOs);
        this.jobLevelToBeAdded = jobLevelToBeAdded;
        this.minSalaryToBeChecked = minSalaryToBeChecked;
    }

    /**
     * @return job degree of type Strin
     */
    public String getJobDegree() {
        return jobDegree;
    }

    /**
     * @return min salary of type double
     */
    public double getMinSalary() {
        return minSalary;
    }

    /**
     * @return max salary of type double
     */
    public double getMaxSalary() {
        return maxSalary;
    }

    /**
     * @return list of levelAndSalaryDTOs
     */
    public List<LevelAndSalaryDTO> getLevelAndSalaryDTOs() {
        return Collections.unmodifiableList(levelAndSalaryDTOs);
    }

    /**
     * @return job level to be added of type int
     */
    public int getJobLevelToBeAdded() {
        return jobLevelToBeAdded;
    }

    /**
     * @return min salary to be checked of type double
     */
    public double getMinSalaryToBeChecked() {
        return minSalaryToBeChecked;
    }

}
