package com.dxc.payroll.services.dto;

import java.time.LocalDate;

/**
 * @author vpazderska
 *
 */
public class IndexationDTO {

    /**
     * Percentage for the indexation DTO object
     */
    private final double percentage;
    /**
     * Date for the indexation DTO object
     */
    private final LocalDate dateOfIndexation;
    /**
     * Job title for the indexation DTO object
     */
    private final String jobTitle;
    /**
     * Job degree for the indexation DTO object
     */
    private final String jobDegree;
    /**
     * Job level for the indexation DTO object
     */
    private final int jobLevel;

    /**
     *
     * Constructor of Indexation DTO object
     *
     * @param percentage
     * @param dateOfIndexation
     * @param jobTitle
     * @param jobDegree
     * @param jobLevel
     */
    public IndexationDTO(final double percentage, final LocalDate dateOfIndexation,
            final String jobTitle, final String jobDegree, final int jobLevel) {
        this.percentage = percentage;
        this.dateOfIndexation = dateOfIndexation;
        this.jobTitle = jobTitle;
        this.jobDegree = jobDegree;
        this.jobLevel = jobLevel;
    }

    /**
     * @return percentage of indexation
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * @return date of indexation
     */
    public LocalDate getDateOfIndexation() {
        return dateOfIndexation;
    }

    /**
     * @return job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @return job title
     */
    public String getJobDegree() {
        return jobDegree;
    }

    /**
     * @return job title
     */
    public int getJobLevel() {
        return jobLevel;
    }

}
