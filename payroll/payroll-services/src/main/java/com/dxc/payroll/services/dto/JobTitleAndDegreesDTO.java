package com.dxc.payroll.services.dto;

import java.util.Collections;
import java.util.List;

/**
 * DTO holding job title and the corresponding job degrees.
 */
public class JobTitleAndDegreesDTO {
    /**
     * The job title.
     */
    private final String jobTitle;
    /**
     * List of job degrees, associated to the given job title.
     */
    private final List<String> jobDegrees;

    /**
     * Constructor for the JobTitleAndDegreesDTO.
     *
     * @param jobTitle
     * @param jobDegrees
     */
    public JobTitleAndDegreesDTO(final String jobTitle, final List<String> jobDegrees) {
        this.jobTitle = jobTitle;
        this.jobDegrees = Collections.unmodifiableList(jobDegrees);
    }

    /**
     * @return jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @return list of job degrees
     */
    public List<String> getJobDegrees() {
        return Collections.unmodifiableList(jobDegrees);
    }

}
