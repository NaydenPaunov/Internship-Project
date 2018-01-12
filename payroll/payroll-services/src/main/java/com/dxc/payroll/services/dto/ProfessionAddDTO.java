package com.dxc.payroll.services.dto;

import java.util.Collections;
import java.util.List;

/**
 * Profession DTO necessary for add new position
 */
public class ProfessionAddDTO {
    /**
     * Private field of type String
     */
    private final String jobTitle;

    /**
     * List of JobWithSalaryDTO objects for the job DTO object
     */
    private final List<JobWithSalaryAddDTO> jobWithSalaryAddDTOs;

    /**
     * Constructor for ProfessionDTO
     *
     * @param jobTitle
     *            of type String, must not be null or empty
     * @param jobWithSalaryAddDTOs,
     *            must not be null
     */
    public ProfessionAddDTO(final String jobTitle,
            final List<JobWithSalaryAddDTO> jobWithSalaryAddDTOs) {
        this.jobTitle = jobTitle;
        this.jobWithSalaryAddDTOs = Collections.unmodifiableList(jobWithSalaryAddDTOs);
    }

    /**
     * @return job title of type String
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @return list of jobjobWithSalaryDTOs
     */
    public List<JobWithSalaryAddDTO> getJobWithSalaryAddDTOs() {
        return Collections.unmodifiableList(jobWithSalaryAddDTOs);
    }

}
