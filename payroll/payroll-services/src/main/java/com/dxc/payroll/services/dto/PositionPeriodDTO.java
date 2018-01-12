package com.dxc.payroll.services.dto;

import java.time.LocalDate;

/**
 * DTO class for Position Period
 */
public final class PositionPeriodDTO {

    /**
     * Private field - jobTitle of type String
     */
    private final String jobTitle;

    /**
     * Private field - jobLevel of type int
     */
    private final int jobLevel;

    /**
     * Private field - baseSalary of type double
     */
    private final double baseSalary;

    /**
     * Private field - startDate of type LocalDate
     */
    private final LocalDate startDate;

    /**
     * Private field - endDate of type LocalDate
     */
    private final LocalDate endDate;

    /**
     * Constructor for PositionPeriodDTO
     *
     * @param jobTitle
     * @param jobLevel
     * @param baseSalary
     * @param startDate
     * @param endDate
     */
    public PositionPeriodDTO(final String jobTitle, final int jobLevel, final double baseSalary,
            final LocalDate startDate, final LocalDate endDate) {
        super();
        this.jobTitle = jobTitle;
        this.jobLevel = jobLevel;
        this.baseSalary = baseSalary;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * getter for jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * getter for jobLevel
     */
    public int getJobLevel() {
        return jobLevel;
    }

    /**
     * getter for baseSalary
     */
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * getter for startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * getter for endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

}
