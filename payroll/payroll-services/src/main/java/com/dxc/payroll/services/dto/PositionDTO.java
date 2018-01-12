package com.dxc.payroll.services.dto;

/**
 *
 */
public class PositionDTO {

    /**
     * Job Title for the PositionDTO object
     */
    private final String jobTitle;
    /**
     * Position Degree for the PositionDTO object
     */
    private final String jobDegree;
    /**
     * Position level for the PositionDTO object
     */
    private final int jobLevel;
    /**
     * Base salary for the PositionDTO object
     */
    private final double baseSalary;

    /**
     * Constructor of PositionDTO object
     *
     * @param jobTitle
     * @param jobDegree
     * @param jobLevel
     * @param baseSalary
     */
    public PositionDTO(final String jobTitle, final String jobDegree, final int jobLevel,
            final double baseSalary) {
        this.jobTitle = jobTitle;
        this.jobDegree = jobDegree;
        this.jobLevel = jobLevel;
        this.baseSalary = baseSalary;
    }

    /**
     * Constructor of PositionDTO object Initialize jobTitle and jobDegree with
     * empty string.Job level with zero and baseSalary with zero;
     * 
     */
    public PositionDTO() {
        this.jobTitle = "";
        this.jobDegree = "";
        this.jobLevel = 0;
        this.baseSalary = 0.00;
    }

    /**
     * @return Job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @return Base salary
     */
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * @return Current job level
     */
    public int getJobLevel() {
        return jobLevel;
    }

    /**
     * @return Current position degree
     */
    public String getJobDegree() {
        return jobDegree;
    }
}
