package com.dxc.payroll.services.dto;

/**
 * Class which holds information for the job level and base salary.
 */
public class LevelAndSalaryDTO {
    /**
     * Private field - job level.
     */
    private final int jobLevel;
    /**
     * Private field - base salary.
     */
    private final double baseSalary;

    /**
     * Constructor of LevelAndSalaryDTO object
     *
     * @param jobLevel
     * @param baseSalary
     */
    public LevelAndSalaryDTO(int jobLevel, double baseSalary) {
        super();
        this.jobLevel = jobLevel;
        this.baseSalary = baseSalary;
    }

    /**
     * Gets the base salary.
     * 
     * @return base salary
     */
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * Gets the job level.
     * 
     * @return job level
     */
    public int getJobLevel() {
        return jobLevel;
    }

}
