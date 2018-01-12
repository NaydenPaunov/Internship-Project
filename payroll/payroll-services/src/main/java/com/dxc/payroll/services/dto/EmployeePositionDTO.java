package com.dxc.payroll.services.dto;

/**
 *
 */
public class EmployeePositionDTO {
    /**
     * Employee name for the EmployeePositionDTO object
     */
    private final String employeeName;
    /**
     * Job Title for the EmployeePositionDTO object
     */
    private final String jobTitle;
    /**
     * Position Degree for the EmployeePositionDTO object
     */
    private final String jobDegree;
    /**
     * Position level for the EmployeePositionDTO object
     */
    private final int jobLevel;
    /**
     * Base salary for the EmployeePositionDTO object
     */
    private final double baseSalary;

    /**
     * Constructor of EmployeePositionDTO object
     *
     * @param employeeName
     * @param jobTitle
     * @param jobDegree
     * @param jobLevel
     * @param baseSalary
     */
    public EmployeePositionDTO(final String employeeName, final String jobTitle,
            final String jobDegree, final int jobLevel, final double baseSalary) {
        this.jobTitle = jobTitle;
        this.jobDegree = jobDegree;
        this.jobLevel = jobLevel;
        this.baseSalary = baseSalary;
        this.employeeName = employeeName;
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

    /**
     * @return Employee name
     */
    public String getEmployeeName() {
        return employeeName;
    }

}
